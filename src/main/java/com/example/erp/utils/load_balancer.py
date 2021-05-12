import os
import pickle
import sys
email=sys.argv[1]
appname=sys.argv[2]
fa=open("/mnt/nfs_share/active.pickle","rb")
# test=open("/home/rahul/Documents/test.txt","w")
# test.write("working")



#for getting the best ip
# fa=open("/mnt/active.pickle","wb")
active=pickle.load(fa)

# fd=open("/mnt/nfs_share/dead.pickle","rb")
fd=open("/mnt/nfs_share/dead.pickle","wb")
dead=pickle.load(fd)
print("Active :::" , active)
print("dead ::" , dead)
dict1 = []
for i in active:
    # load(i)
    dict1.append(i)
ip=dict1[0]


# ip='192.168.29.49'
dict1.sort(key = lambda x: x[1])
# command="python3 "+job_path
# os.mkdir("/mnt/nfs_share/"+email+"/"+appname+".py")
# os.system("python3 /./mnt/nfs_share/newfolder/AI-on-the-edge/send.py "+ip+" "+email+" "+"python3 "+appname+".py")
path="/mnt/nfs_share/"+email+"/"
#making a directory for a app to store completed.txt
# os.makedirs(path+appname,exist_ok=True)
# temp=open(path+appname+"/"+"completed.txt","w")
# temp.write("0")
# temp.close()
os.system("python3 /mnt/nfs_share/newfolder/AI-on-the-edge/send.py "+ip+" "+email+" "+appname+" "+"python3 "+path+appname+".py")
