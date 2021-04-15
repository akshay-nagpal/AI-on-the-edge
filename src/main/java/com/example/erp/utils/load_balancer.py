import os
import pickle
import sys
email=sys.argv[1]
appname=sys.argv[2]
fa=open("/mnt/nfs_share/active.pickle","rb")
active=pickle.load(fa)

fd=open("/mnt/nfs_share/dead.pickle","rb")
dead=pickle.load(fd)
print("Active :::" , active)
print("dead ::" , dead)
dict1 = []
for i in active:
    # load(i)
    dict1.append(i)
ip=dict1[0]
dict1.sort(key = lambda x: x[1])
# command="python3 "+job_path
os.mkdir("/mnt/nfs_share/"+email+"/"+appname+".py")
os.system("python3 /./mnt/nfs_share/newfolder/AI-on-the-edge/send.py "+ip+" "+email+" "+"python3 "+appname+".py")

