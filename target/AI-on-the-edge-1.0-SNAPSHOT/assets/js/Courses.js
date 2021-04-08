var email = localStorage.getItem("email_id");
localStorage.setItem("count",0);
window.onload=fetch_courses(email);
function encode(data){
    let query=data.url;
    query+='?'+encodeURI("email_id")+'='+encodeURI(data.params.email_id);
    return query;
}
let faculty;
let prereq;
let done;
async function fetch_courses(email) {
    data = {
        url: 'api/students',
        params: {
            email_id: email
        }
    }
    // let response = await fetch(encode(data));
    // let master =  await response.json(); // read response body and parse as JSON
    //console.log(master);
    // var temp=JSON.stringify(master);

    fetch(encode(data)).then(response => response.json().then(data => ({
            data: data,
            status: response.status
        })
    ).then(res => {
        faculty = res.data.fac;
        done = res.data.done;
        prereq = res.data.preq;
        console.log(faculty);
        console.log(prereq);
        console.log(done);
        var courses = [];
        for (var i in faculty) {
            if (faculty[i].capacity)
                courses.push(i)
        }

        var eligible = [];
        for (var i in courses) {
            var flag = 1;
            var flag2 = 0;
            var size;
            if (prereq[courses[i]]) size = prereq[courses[i]].length;
            else size = 0;
            for (var j = 0; j < size; j++) {
                flag2 = 1;
                flag = 0;
                // for (var k in done) {
                //     flag = 0;
                //     if (prereq[courses[i]][j] == done[k]) {
                //         flag = 1;
                //         break;
                //     }
                if (done.includes(prereq[courses[i]][j])) {
                    flag = 1;
                }
                if (flag == 0) break;
            }

        if (flag2 == 0) {
            if (!done.includes(courses[i]))
                eligible.push(courses[i])
        }
        if (flag == 1 && flag2 == 1) {
            if (!done.includes(courses[i]))
                eligible.push(courses[i])
        }
    }
        console.log(eligible);
        // let courses_option = document.getElementById('course_list');
        // for(let i = 0 ; i<eligible.length ; i++){
        //     // courses_option.in
        //     courses_option.innerHTML = '<input type="checkbox"> eligible[i]</input>';
        // }
        // console.log("hiii");
        loopThroughList(eligible,faculty,done,prereq);
    }));
}
function loopThroughList(eligible,faculty,done,prereq){
    let lists = document.getElementById("course_list");
    let colors = ["#007bff", "#5f2013", "#c2c428", "#c428b1", "#57c428"];
    for (let i = 0 ; i<eligible.length; i++){
        let tr=document.createElement("tr");
        let td1=document.createElement('td');
        let td2=document.createElement('td');
        let td3=document.createElement('td');
        let td4=document.createElement('td');
        let td5=document.createElement('td');
        let td6=document.createElement('td');
        // td1.setAttribute("id",i.toString());
        td1.innerHTML='<input type=\"checkbox\" id='+i.toString()+ ' '+ 'onclick=fun(this.id);>';
        td2.innerText=eligible[i];
        td3.innerText=faculty[eligible[i]].course_name;
        td4.innerText=faculty[eligible[i]].name;
        td5.innerText=faculty[eligible[i]].credits;
        var str="";
        for(var j in prereq[eligible[i]])
            str+=" "+prereq[eligible[i]][j];
        td6.innerText=str;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        lists.appendChild(tr);
    }
    let done_list = document.getElementById("done_list");
    for(var i=0;i<done.length;i++)
    {
        let tr=document.createElement("tr");
        let td1=document.createElement("td");
        let td2=document.createElement("td");
        td1.innerText=done[i];
        td2.innerText=faculty[done[i]].course_name;
        tr.appendChild(td1);
        tr.appendChild(td2);
        done_list.appendChild(tr);
    }
    localStorage.setItem("count",0);
    localStorage.setItem("eligible", JSON.stringify(eligible));
    localStorage.setItem("done",JSON.stringify(done));
    // '<li class=\"list-group-item\">'+anime_characters[i]+'</li>'
}
async function update()
{
    var eligible = JSON.parse(localStorage.getItem("eligible"));
    var done=JSON.parse(localStorage.getItem("done"));
    var inputElems = document.getElementsByTagName("input");
    var  count = 0;
    var checked=[];
    for (var i=0; i<inputElems.length; i++) {
        if (inputElems[i].type == "checkbox" && inputElems[i].checked == true){
            count++;
             checked.push(eligible[parseInt(inputElems[i].id,10)]);
        }
    }
    console.log(checked);
    for(var i in done)
    {
        checked.push(done[i]);
    }
    if(count<4)
        alert("Select atleast 4 courses");
    else {
        let loading=document.getElementById('loading');
        loading.style.display='block';
        let response = await fetch('api/students/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify({
                credits: count*4,
                email: email,
                courses: checked
            })
        });
        window.location="ThankYou.html";
    }
}
