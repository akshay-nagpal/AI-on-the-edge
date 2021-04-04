import mysql.connector
from collections import defaultdict
import socket
import pickle
import os

mydb = mysql.connector.connect( host="localhost",user="root",password="rahul166",database="SPE")
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
    response=os.system("ping -w 5 " + k)
#     sock=socket(socket.AF_INET, socket.SOCK_STREAM)
#     response=sock.connect_ex((k,))
    print(response)
    if response==0:
        active.append(k)
    else:
        dead.append(k)


print("Active :::" , active)
print("dead ::" , dead)



