import sys
import os
#n=len(sys.argv)
#name of the file i.e ip
name=sys.argv[1]
def fun(name):
    print(name,"hi")

    command="touch {name}.txt".format(name=name)

    os.system(command)
    # events_per_sec="sysbench cpu --threads=2 run | awk '/events per second:/ {print $4}' >/./mnt/nfs_share/"+name+".txt"
    events_per_sec="echo 3000 > /mnt/nfs_share/"+name+".txt"
    os.system(events_per_sec)
   

    cpu_percent="grep 'cpu ' /proc/stat | awk '{usage=($2+$4)*100/($2+$4+$5)} END {print usage}' >> /mnt/nfs_share/"+name+".txt"
    os.system(cpu_percent)

    free_mem_percent="free | grep Mem | awk '{print $4/$2 * 100.0}' >>  /mnt/nfs_share/"+name+".txt"
    os.system(free_mem_percent)

    actual_free_ram="free | grep Mem | awk '{print $4/(1024*1024)}'>> /mnt/nfs_share/"+name+".txt"
    os.system(actual_free_ram)

    get_temp="sensors | perl -ne 'if (/^Core \d+:\s+\+(.*?)°C/) { $s += $1; $c++; } if ($c==0) {$c++; $c;} END { printf($s/$c) }' >> /mnt/nfs_share/"+name+".txt"
    os.system(get_temp)

    ip="echo "+name+"" ">> /mnt/nfs_share/"+name+".txt"
    os.system(ip)
    
  #  space="echo \n >>/./mnt/nfs_share/"+name+".txt"
   # os.system(space)




fun(name)
#critical_temp="sysbench cpu --threads=2 run | awk '/events per second:/ {print $4}' >>/./mnt/nfs_clientshare/"+name+".txt"
#os.system(critical_temp)

