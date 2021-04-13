import pika
import sys
import os
from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.schedulers.background import BlockingScheduler
active=0
ack_flag='0'
ip = sys.argv[1]
out_path=sys.argv[2]
job_path=sys.argv[3:]
print(job_path)
print(ip+" "+out_path+" ")
def run():
    response=os.system("ping -w 1 " + ip)
    if response==0:
        active=1
        # return 1
    else:
        active=0
        # return 0
    if(active==0 and ack_flag!='1'):
        os.system("python3 /./mnt/nfs_share/newfolder/AI-on-the-edge/src/main/java/com/example/erp/utils/load_balancer.py "+job_path[0]+" "+job_path[1])
        sys.exit(0)
    if(ack_flag=='1'):
        print("job completeeeddd")
        f=open("/mnt/nfs_share/"+out_path+"/completed.txt")
        f.write(1)
        print("job completed")
        sys.exit(0)
    # print(active)
scheduler = BlockingScheduler()
scheduler2=BackgroundScheduler()
# scheduler.add_job(run, 'cron', second='*/5')
scheduler2.start()
#change this
credentials = pika.PlainCredentials(username='test', password='test')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost',credentials=credentials))
channel = connection.channel()

# channel.confirm_delivery()
channel.exchange_declare(exchange='jobs', exchange_type='direct')

ack_connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
ack_channel = ack_connection.channel()
q=ack_channel.queue_declare(queue='ackqueue')
message = ' '.join(sys.argv[3:]) or 'DEFAULT MESSAGE'
# pika.queue_purge('ackqueue')
os.system('echo 2122 | sudo -S rabbitmqctl purge_queue ackqueue')
channel.basic_publish(
    exchange='jobs', routing_key=ip, body=message, properties=pika.BasicProperties(content_type='text/plain',
                                                                                       delivery_mode=1))

scheduler2.add_job(run,'cron',second='*/30')
# print(" [x] Sent %r:%r" % (severity, message))
def callback(ch, method, properties, body):
    print("ACK recv :",body)
    ack_flag=str(body,'UTF-8')
    print(ack_flag)
    return ack_flag

ack_channel.basic_consume(
    queue='ackqueue', on_message_callback=callback)

ack_channel.start_consuming()
print("Reached2")

connection.close()


