import os
import pickle
import system
job_path=sys.argv[1]
fa=open("active.pickle","rb")
active=pickle.load(fa)

fd=open("dead.pickle","rb")
dead=pickle.load(fd)
print("Active :::" , active)
print("dead ::" , dead)
dict1 = []
for i in active:
    # load(i)
    dict1.append((i,score(i)))

dict1.sort(key = lambda x: x[1])
os.system("python3 /./mnt/nfs_share/newfolder/AI-on-the-edge/send.py ")

print(dict1)
with open("/./mnt/nfs_share/newfolder/AI-on-the-edge/src/main/java/com/example/erp/visual/score.json", "w") as outfile:
    for tup in dict1:
        temp={}
        temp['ip'] = tup[0]
        temp['score'] = tup[1]
        json.dump(temp, outfile)