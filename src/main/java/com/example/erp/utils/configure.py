import os
from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.schedulers.background import BlockingScheduler
os.system("sudo mount 192.168.43.132:/mnt/nfs_share  /mnt/nfs_clientshare")
def run():
   os.system("python3 /./mnt/nfs_clientshare/load.py "+ip+"")
scheduler = BlockingScheduler()
scheduler2=BackgroundScheduler()
scheduler2.add_job(run,'cron',second='*/10')
scheduler2.start()