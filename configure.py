import os
import sys
from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.schedulers.background import BlockingScheduler
ip=sys.argv[1]
os.system("sudo mount 192.168.43.132:/mnt/nfs_share  /mnt/nfs_share")
def run():
   os.system("python3 /./mnt/nfs_share/load.py "+ip+"")
scheduler = BlockingScheduler()
scheduler2=BackgroundScheduler()
scheduler2.add_job(run,'cron',second='*/10')
scheduler2.start()
os.system("python3 /./mnt/nfs_share/newfolder/AI-on-the-edge/recv.py "+ip+"")