let jobbutton=document.getElementById("runjob")
console.log("Reached job.js");
var jobs=[];
let map=new Map();
function encode(url,email,appname){
    let query=url;
    query+='?'+encodeURI("item")+'='+encodeURI(email);
    query+='&'+encodeURI("item")+'='+encodeURI(appname);
    return query;
}
jobbutton.addEventListener('click', async (e) => {
    alert("clicked");
    let appname=document.getElementById("applicationname").value;
    let email=window.localStorage.getItem("email_id");
    console.log(appname);

    map.set(email,appname);
    console.log("in js");
    console.log(map);
    let response = fetch('api/job/schedule', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            email: window.localStorage.getItem("email_id"),
            appname : appname
        })
    });
    //     .then(function () {
    //     let res=fetch(encode("api/job/status",email,appname));
    //     console.log(res);
    // });
    var ret=await fetch(encode("api/job/status",email,appname));
    job={'name':appname,'status':1};
    displayJobsTable(job,email);
    // let gresponse = fetch(encode("api/job/status",email,appname));
    //     console.log(gresponse);
    // fetch(encode("api/job/status",email,appname)).then(response => response.json().then(data => ({
    //         data: data,
    //         status: response.status
    //     })
    // ).then(res=>{
    //     console.log(res.data);
    // }));
});

function displayJobsTable(job,email){
        let lists=document.getElementById("job_list");
        let tr=document.createElement("tr");
        let td1=document.createElement("td");
        let td2=document.createElement("td");
        let downlaod=document.createElement("td");
        // downlaod.innerHTML='<button type=\"submit\" id='+email+' '+'onclick=fun(this.id);>Download</button>';
        let url=encode('api/job/download',email,job.name);
        downlaod.innerHTML='<a href='+url+'>Download</a>';
        td1.innerText=job.name;
        td2.innerText=job.status;

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(downlaod);
        lists.appendChild(tr);
    jobs.push(job);
    window.localStorage.setItem("map",map);
    console.log(jobs);

}
function fun(id){
    console.log("in dash"+id);
    console.log(map);
    console.log(map.get(id)+"is clicked");
    let email=id;
    let appname=map.get(id);
    let res=fetch(encode("api/job/download",id,appname));
    console.log(res);
}




