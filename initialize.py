import subprocess
import sys
import os
import time
os.system("pip3 install schedule")
import schedule
os.system("pip3 install apscheduler")
from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.schedulers.background import BlockingScheduler
print ("start")
ip=sys.argv[1]
# user=sys.argv[2]
os.system("sudo apt update")
os.system("sudo apt install nfs-common -y")
os.system("sudo mkdir -p /mnt/nfs_share")
os.system("sudo mount 192.168.29.132:/mnt/nfs_share  /mnt/nfs_share")
os.system("sudo echo '192.168.29.132:/nfsshare /mnt  nfs defaults 0 0' >>sudo /etc/fstab")
os.system("sudo apt install sysbench")
os.system("sudo apt-get install lm-sensors ")
# os.system("sudo apt install openssh-server")
# os.system("ssh-keygen")
os.system("pip3 install pika")
os.system("sudo sensors-detect ")
os.system("sudo service kmod start")
def run():
   os.system("python3 /mnt/nfs_share/newfolder/AI-on-the-edge/load.py "+ip+"")
scheduler = BlockingScheduler()
scheduler2=BackgroundScheduler()
scheduler2.add_job(run,'cron',second='*/10')
# scheduler.add_job(run, 'cron', second='*/5')
scheduler2.start()
os.system("python3 /mnt/nfs_share/newfolder/AI-on-the-edge/recv.py "+ip+"")
# scheduler.every(5).seconds.do(run)0
# while(True):
#     schedule.run_pending()
print ("end")




