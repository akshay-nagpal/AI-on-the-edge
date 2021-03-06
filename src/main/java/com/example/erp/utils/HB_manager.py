import json
import os
# os.system("pip3 install mysql-connector-python")
import mysql.connector
from collections import defaultdict
import pickle
def load(name):
    print(name,"hi")
    command="cd ./mnt/nfs_share/newfolder/AI-on-the-edge/src/main/java/com/example/erp/visual"
    os.system(command)
    command="touch "+name+".txt"

    os.system(command)
    events_per_sec="sysbench cpu --threads=2 run | awk '/events per second:/ {print $4}' > "+name+".txt"
    os.system(events_per_sec)

    cpu_percent="grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}' >> "+name+".txt"
    os.system(cpu_percent)

    free_mem_percent="free | grep Mem | awk '{print $4/$2 * 100.0}' >> "+name+".txt"
    os.system(free_mem_percent)

    actual_free_ram="free | grep Mem | awk '{print $4/(1024*1024)}'>> "+name+".txt"
    os.system(actual_free_ram)


    get_temp="sensors | perl -ne 'if (/^Core \d+:\s+\+(.*?)°C/) { $s += $1; $c++; } END { printf($s/$c) }' >> "+name+".txt"
    os.system(get_temp)


def score(name):
    command="cd /./mnt/nfs_share/"
    # os.system(command)
    full_name="/./mnt/nfs_share/{name}.txt".format(name=name)
    f=open(full_name)

    lst=f.readlines()[:-1]
    lst=[float(i.replace('\n','')) for i in lst]
    print(lst)

    free_cpu=lst[1]

    free_mem=lst[2]

    load=(1/((3/free_cpu)+(1/free_mem)))

    free_RAM=lst[3]

    current_temperature=lst[4]

    number_of_events_per_sec=lst[0]

    performance = ((number_of_events_per_sec) + min(2, free_RAM) / 10)

    Score = load * performance * (current_temperature)
    if(current_temperature >= 100):
        Score=0

    return Score

mydb = mysql.connector.connect( host="192.168.29.132",port="8085",user="test",password="test",database="platformdb",auth_plugin='mysql_native_password')
mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM Resource")
myresult = mycursor.fetchall()
mappping=defaultdict(lambda x:[])
#Constructing mapping
for x in myresult:
    mappping[x[1]]=[x[3],x[2]]

    #Constructing dead and active lists
    print(x)
dead=[]
active=[]


mapping=dict(mappping)
print(mapping)

for k,v in mapping.items():
    #     print(k,v)
    #     if k=='192.168.43.49':
    #         continue
    response=os.system("ping -w 1 " + k)
    #     sock=socket(socket.AF_INET, socket.SOCK_STREAM)
    #     response=sock.connect_ex((k,))
    print(response)
    if response==0:
        active.append(k)
    else:
        dead.append(k)


print("Active :::" , active)
print("dead ::" , dead)
fa=open("/./mnt/nfs_share/active.pickle","wb")
os.system("chmod 777 /./mnt/nfs_share/active.pickle")
# fa=open("/home/rahul/Documents/active.pickle","wb")
pickle.dump(active,fa)
fd=open("/./mnt/nfs_share/dead.pickle","wb")
os.system("chmod 777 /./mnt/nfs_share/dead.pickle")
# fd=open("/home/rahul/Documents/dead.pickle","wb")
pickle.dump(dead,fd)

dict1 = []
for i in active:
    # load(i)
    dict1.append((i,score(i)))

dict1.sort(key = lambda x: x[1])

print(dict1)
with open("/./usr/local/tomcat/webapps/AI/score.json", "w") as outfile:
    lst=[]
    for tup in dict1:
        temp={}
        temp['ip'] = tup[0]
        temp['score'] = tup[1]
        lst.append(temp)
    print(lst)
    json.dump(lst, outfile)
# with open("/home/sachin/Desktop/spe_major/AI-on-the-edge/src/main/java/com/example/erp/utils/file.txt", 'w') as target:# specify path or else it will be created where you run your java code
#     for tup in dict1:
#         target.write(tup[0])
