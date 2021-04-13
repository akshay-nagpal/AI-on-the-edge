import pika
import sys
import os
#change this 
credentials = pika.PlainCredentials(username='test', password='test')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.43.132',credentials=credentials))
channel = connection.channel()

channel.exchange_declare(exchange='jobs', exchange_type='direct')

result = channel.queue_declare(queue='', exclusive=True)
queue_name = result.method.queue

ip = sys.argv[1]

#Apply some error handling
ack_connection = pika.BlockingConnection(pika.ConnectionParameters(host='192.168.43.132',credentials=credentials))
ack_channel = ack_connection.channel()
ack_channel.queue_declare(queue='ackqueue')


channel.queue_bind(
    exchange='jobs', queue=queue_name, routing_key=ip)

print(' [*] Waiting for jobs. To exit press CTRL+C')


#this the function the will execute the task
def callback(ch, method, properties, body):
    print(" [x] %r:%r" % (method.routing_key, body))
    os.system(body)
    print("DONE!!")
    send_ack()

def send_ack():
    ack_channel.basic_publish(exchange='', routing_key='ackqueue',body='1')
    return

channel.basic_consume(
    queue=queue_name, on_message_callback=callback, auto_ack=True)

channel.start_consuming()





