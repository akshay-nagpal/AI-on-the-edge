let jobbutton=document.getElementById("runjob")
console.log("Reached job.js");
var jobs=[];
jobbutton.addEventListener('click', async (e) => {
    alert("clicked");
    let appname=document.getElementById("applicationname").value;
    console.log(appname);
    job={'name':appname,'status':0};
    displayJobsTable(job);
    let response = await fetch('api/job/schedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: window.localStorage.getItem("email_id"),
            appname : appname
        })
    });

});

function displayJobsTable(job){
    let lists=document.getElementById("job_list");
        let tr=document.createElement("tr");
        let td1=document.createElement("td");
        let td2=document.createElement("td");

        td1.innerText=job.name;
        td2.innerText=job.status;

        tr.appendChild(td1);
        tr.appendChild(td2);

        lists.appendChild(tr);
    jobs.push(job);
    console.log(jobs);

}





