
let student_form = document.getElementById('student-validation');
// let course_form = document.getElementById('course-validation');
// window.onload = fetch_courses;

if(student_form) {
    student_form.addEventListener('submit', async (e) => {
        e.preventDefault();
        e.stopPropagation();
        let loading=document.getElementById('loading');
        loading.style.display='block';
        var usertype=window.localStorage.getItem("usertype");
        console.log(usertype);
        if (student_form.checkValidity() === true) {
            let response = await fetch('api/login/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    email: document.getElementById('email').value,
                    password: document.getElementById('inputPassword').value,
                    usertype:usertype,
                })
            });
            let result = await response;
            let email = document.getElementById('email').value;
            let password=document.getElementById('inputPassword').value;
            let logindiv=document.getElementById('login');
            console.log(result);
            if (result['status'] == 200) {
                console.log("In else :" + result['status']);
                window.localStorage.setItem("email_id", email);
                if(usertype == 3) {
                    window.location= "Upload.html";
                }
                if(usertype==1)
                {
                    window.location="score.html";
                }

                else
                {
                    window.location = "ThankYou.html";
                }

                //fetch_courses(email);
            } else {
                loading.style.display='none';
                // let logindiv = document.getElementById('login');
                logindiv.style.display = "block";
            }
        }
        // student_form.classList.add('was-validated');
    });
}

// course_form.onsubmit = async (e) => {
//     e.preventDefault();
//     e.stopPropagation();
//     if (course_form.checkValidity() === true) {
//       let form_data = new FormData();
//       form_data.append('name', document.getElementById('name').value);
//       form_data.append('description', document.getElementById('description').value);
//       form_data.append('credits', document.getElementById('credits').value);
//         // $.ajax({
//         //   type: "POST",
//         //   url: "api/courses/register",
//         //   enctype: 'multipart/form-data',
//         //   data: form_data,
//         //   processData: false,
//         //   contentType: false,
//         // }).done(function(response, status) {
//         //   console.log(response, status);
//         // });
//         let response = await fetch('api/courses/register', {
//         method: 'POST',
//         body: form_data
//       });
//       let result = await response;
//       console.log(result);
//     }
//     course_form.classList.add('was-validated');
// };
function encode(data){
    let query=data.url;
    query+='?'+encodeURI("email_id")+'='+encodeURI(data.params.email_id);
    return query;
}
let faculty;
let prereq;
let done;
async function fetch_courses(email){
    data={
        url:'api/students',
        params:{
            email_id:email
        }
    }
    // let response = await fetch(encode(data));
    // let master =  await response.json(); // read response body and parse as JSON
    //console.log(master);
    // var temp=JSON.stringify(master);

    fetch(encode(data)).then(response=>response.json().then(data=>({
        data: data,
        status:response.status
    })
    ).then(res=>{
        faculty=res.data.fac;
        done=res.data.done;
        prereq=res.data.preq;
        console.log(faculty);
        console.log(prereq);
        console.log(done);
        var courses=[];
        for(var i in faculty)
        {
            if(faculty[i].capacity)
            courses.push(i)
        }

        var eligible=[];
        for(var i in courses)
        {
            var flag=1;
            for(var j in prereq[courses[i]])
            {
                for(var k in done)
                {
                    flag=0;
                    if(prereq[courses[i]][j]==done[k])
                    {
                        flag=1;
                        break;
                    }
                }
                if(flag==0) break;
            }
            if(flag==1)
            eligible.push(courses[i])
        }
        console.log(eligible);
        window.location="Course.html";
        // let courses_option = document.getElementById('course_list');
        // for(let i = 0 ; i<eligible.length ; i++){
        //     // courses_option.in
        //     courses_option.innerHTML = '<input type="checkbox"> eligible[i]</input>';
        // }
        // console.log("hiii");
    }));
    //     for(var i in faculty)
    //     {
    //         courses.push(i);
    //     }
    //     var hash=new Object();
    //     for(var i in courses)
    //     {
    //         hash[courses[i]]=[];
    //     }
    //     for(var i in prereq) {
    //         for (var j in prereq[i]) {
    //             hash[prereq[i][j]].push(i);
    //         }
    //     }
    //     console.log(hash);
    //     var indegree=new Object();
    //     for(var i in courses)
    //     {
    //         indegree[courses[i]]=0;
    //     }
    //     for(var i in prereq)
    //     {
    //         indegree[i]=prereq[i].length;
    //     }
    //     console.log(indegree);
    //     var totalcredits=0;
    //     var courselist=[];
    //     for(var i in indegree)
    //     {
    //         if(!indegree[i])
    //         {
    //             courselist.push(i);
    //         }
    //     }
    //     while(!courselist.length)
    //     {
    //         courselist
    //     }
    // });
    // var courses=[];
    // console.log(faculty);
    // for(var i in faculty)
    // {
    //     courses.push(i);
    // }
    // var hash=new Object();
    // for(var i in courses)
    // {
    //     hash[courses[i]]=[];
    // }
    // for(var i in prereq) {
    //     for (var j in prereq[i]) {
    //         hash[prereq[i][j]].push(i);
    //     }
    // }
    // console.log(hash);

   // console.log(temp);
    // console.log(courses);
    //console.log(master['preq']);
    // window.location="Course.html";

}