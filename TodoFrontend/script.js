// Shared script for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";
const token = localStorage.getItem("token");

// Login page logic
function login() {

     const email=document.getElementById("email").value;
    const password=document.getElementById("password").value;
    fetch(`${SERVER_URL}/Auth/login`,{
        method:"post",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({email:email,password:password})
    })
    .then(Respons=>{
        if(!Respons.ok){
            alert("login failed")
            throw new Error("failed");

        
        }
        return Respons.json();
    }
    )
    .then(body=>{
        alert("login succeessfully")
        localStorage.setItem("token",body.token);
        window.location.href="todos.html";
         
    })
    .catch(eror=>{
        alert(eror.message);
    })

}

// Register page logic
function register() {
    const email=document.getElementById("email").value;
    const password=document.getElementById("password").value;
    fetch(`${SERVER_URL}/Auth/register`,{
        method:"post",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({email:email,password:password})
    })
    .then(Respons=>{
        if(Respons.ok){
            alert("registration successful")
            window.location.href="login.html"
        }else{
            return Respons.json() .then(body=>{
                throw new Error(body.message || "registration failed")
            })
        }
    }
    ).catch(eror=>{
        alert(eror.message);
    })

}

// Todos page logic
function createTodoCard(todo) {
    const card=document.createElement("div");
    card.className="todo-card";

    const checkbox=document.createElement("input");
    checkbox.type="checkbox"
    checkbox.checked=todo.isTrue;
   checkbox.addEventListener("change",()=>{
   const updatetodo = {
    id: todo.id,
    Title: todo.Title,
    isTrue: checkbox.checked
};

    if(checkbox.checked){
        span.style.textDecoration = "line-through";
        span.style.color = "#aaa";
    }else{
        span.style.textDecoration = "none";
        span.style.color = "black";
    }
    console.log("checkbox:", checkbox.checked);
console.log("updated todo:", updatetodo);

    updateTodoStatus(updatetodo);
})
    const span=document.createElement("span");
    span.textContent=todo.Title;
    if(todo.isTrue){
        span.style.textDecoration="line-through";
        span.style.color="#aaa";
    }
    const dltbutton=document.createElement("button");
    dltbutton.textContent="X";
    dltbutton.onclick=()=>{
            deleteTodo(todo.id);
    }

    card.appendChild(checkbox);
    card.appendChild(span);
    card.appendChild(dltbutton);
    return card;


}

function loadTodos() {
    fetch(`${SERVER_URL}/findAll`,{
        method:"GET",
        headers:{Authorization:`Bearer ${token}`}
    })
    .then(respons=>{
        if(!respons.ok){
            throw new Error("failed to get todos");
        }
        return respons.json()
    })
    .then(todos=>{
         console.log("FROM BACKEND:", todos);

       const todolist=document.getElementById("todo-list")
       todolist.innerHTML=""
        if(!todos || todos.length===0){
            todolist.innerHTML=`<p id="empty-message">No todos</p>`;

        }else{
            todos.forEach(element => {todolist.append(createTodoCard(element));
                
            });
        }

    })
    .catch(error=>{
        alert(error.message);
    })

}

function addTodo() {
    const input = document.getElementById("new-todo")
    const todotext=input.value.trim()

     fetch(`${SERVER_URL}/create`,{
        method:"post",
        headers:{"content-type":"application/json", Authorization:`Bearer ${token}`},
        body:JSON.stringify({Title:todotext,isTrue:false}) 
    })
    .then(response=>{
        if(!response.ok){
            throw new Error("failed to add todo");
        }
        return response.json()
    })
    .then(()=>{
        loadTodos();
    })
    .catch(error=>{
        alert(error.message);
    })


}

function updateTodoStatus(todo) {
    fetch(`${SERVER_URL}`,{
        method:"put",
        headers:{"content-type":"application/json", Authorization:`Bearer ${token}`},
        body:JSON.stringify(todo)
    })
    .then(response=>{
        if(!response.ok){
            throw new Error("failed to update todo");
        }
        return response.json()
    })
    .then(()=>{
        loadTodos();
    })
    .catch(error=>{
        alert("failed to update");
    })

}

function deleteTodo(id) {
    fetch(`${SERVER_URL}/delete?id=${id}`,{
        method:"delete",
        headers:{"Authorization":`Bearer ${token}`}
    })
    .then(body=>{
        if(!body.ok){
            throw new Error("deletion failed");
        }
        
    })
    .then(response=>{
        loadTodos()
    })
    .catch(error=>{
        alert("deletion failed");
    })
    

}

// Page-specific initializations
document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});
