import pika
import sys

#change this 
credentials = pika.PlainCredentials(username='test', password='test')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost',credentials=credentials))
channel = connection.channel()


channel.exchange_declare(exchange='jobs', exchange_type='direct')

ip = sys.argv[1] 
message = ' '.join(sys.argv[2:]) or 'DEFAULT MESSAGE'
channel.basic_publish(
    exchange='jobs', routing_key=ip, body=message)
# print(" [x] Sent %r:%r" % (severity, message))
connection.close()


