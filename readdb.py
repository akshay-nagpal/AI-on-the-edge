import mysql.connector
from collections import defaultdict
import socket
import pickle
import os
#import load
#import scheduler
import sys

#name=sys.argv[1]
import sys
import os
#n=len(sys.argv)
import temp
#name of the file i.e ip
#name=sys.argv[1]
def load(name):
    print(name,"hi")

    command="touch {name}.txt".format(name=name)

    os.system(command)
    events_per_sec="sysbench cpu --threads=2 run | awk '/events per second:/ {print $4}' >/./mnt/nfs_clientshare/"+name+".txt"
    os.system(events_per_sec)

    cpu_percent="grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}' >> /./mnt/nfs_clientshare/"+name+".txt"
    os.system(cpu_percent)

    free_mem_percent="free | grep Mem | awk '{print $4/$2 * 100.0}' >>  /./mnt/nfs_clientshare/"+name+".txt"
    os.system(free_mem_percent)

    actual_free_ram="free | grep Mem | awk '{print $4/(1024*1024)}'>> /./mnt/nfs_clientshare/"+name+".txt"
    os.system(actual_free_ram)


    get_temp="sensors | perl -ne 'if (/^Core \d+:\s+\+(.*?)°C/) { $s += $1; $c++; } END { printf($s/$c) }' >>/./mnt/nfs_clientshare/"+name+".txt"
    os.system(get_temp)


def score(name):

    full_name="{name}.txt".format(name=name)

    f=open(full_name)

    lst=f.readlines()
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



mydb = mysql.connector.connect( host="localhost",user="root",password="Sachin@123",database="SPE")
mycursor = mydb.cursor()
mycursor.execute("SELECT * FROM Mapping")
myresult = mycursor.fetchall()
mappping=defaultdict(lambda x:[])
#Constructing mapping 
for x in myresult:
  mappping[x[0]]=[x[1],x[2]]

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

dict1 = []
for i in active:
  load(i)
  dict1.append((i,score(i)))

print(dict1)
