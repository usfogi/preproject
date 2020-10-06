$(document).ready (printUsers());

function addUser(){
    var roles='?roles=';
    var newRoles = $('#newRoles').val();
    newRoles.forEach(function (item){
        roles+=item +',';
    })
    var roleFromForm =  document.getElementById("newRoles");
    roleValue = roleFromForm[roleFromForm.selectedIndex].value;

    var roles1=roles.substr(0,roles.length-1);
    var jsonVar = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value,
        password: document.getElementById("exampleInputPassword1").value,
    };

    const response = fetch('http://localhost:8080/admin/users'+roles1, {
        method: 'POST',
        body: JSON.stringify(jsonVar),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function () { document.location.reload() });

        document.getElementById('table-tab').click();
        $("#usersTable > tbody").empty();
        printUsers();

}

function printUsers() {
    var responce = fetch('http://localhost:8080/admin/users')
        .then((response) => {
            response.json().then((data) => {
                data.forEach(function (r) {
                    var roles = r.roles;
                    var stringRoles = '';
                    roles.forEach(function (item, i, roles) {
                        stringRoles += item.role + ' ';
                    })
                    console.log(r.name);
                    $('#usersTable').append(`<tr id='${r.id}'>
                        <td>${r.id}</td>
                        <td>${r.name}</td>
                        <td>${r.email}</td>
                        <td>${stringRoles}</td>
                        <td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModalCenter" onclick="editUser(this)">Edit</button></td>
                        <td><button type="button"  class="btnDelete btn btn-danger" data-toggle="modal" data-target="#deleteModalCenter" onclick="deleteRow(this)">Delete</button></td>
                    </tr>`);
                });
            });
        });
}

function deleteRow(o) {
    userId = $(o).closest('tr').find('td').eq(0).text();
    document.getElementById('deleteForm').reset();
    var url = 'http://localhost:8080/admin/users/'+ userId;
    fetch(url)
        .then((response) => {
            response.json().then((data) => {
                $('#idDelete').val(data.id);
                $('#nameDelete').val(data.name);
                $('#emailDelete').val(data.email);
                var roles = data.roles;
                console.log(roles);
                var newRoles=[];
                $('#newRoles option').each(function() {
                    newRoles[ $(this).val()] = $(this).val();
                });
                console.log(newRoles)
                roles.forEach(function (item) {
                    if(newRoles.includes(String(item.id))) {
                        $('#deleteFormControlSelect option[id='+String(Number(item.id+2))+']').prop('selected', true);
                    }
                })
            });
        });
};

function deleteUser() {
    var url = 'http://localhost:8080/admin/users/'+ userId;
    fetch(url, {method: 'DELETE',})
        .then(res => res.text()) // or res.json()
        .then(res => console.log(res))
    var table = document.getElementById("usersTable");
    var selector = "tr[id='"+userId+"']";
    var row = table.querySelector(selector);
    row.parentElement.removeChild(row);
}

function getUser() {
    $("#userInfo > tbody").empty();
    var id = document.getElementById("userId").value;
    var url = 'http://localhost:8080/admin/users/' + id;
    fetch(url)
        .then((response) => {
            response.json().then((data) => {
                var roles = data.roles;
                var stringRoles = '';
                roles.forEach(function (item,) {
                    stringRoles += item.role + ' ';
                })
                $('#userInfo').append(`<tr>
                    <td>${data.id}</td>
                    <td>${data.name}</td>
                    <td>${data.email}</td>
                    <td>${stringRoles}</td>
                </tr>`);
            })
        })
}

function cleanForm() {
    document.getElementById('addForm').reset();
}

function editUser(o) {
    document.getElementById('editForm').reset();
    var id = $(o).closest('tr').find('td').eq(0).text();
    var url = 'http://localhost:8080/admin/users/'+ id;
    fetch(url)
        .then((response) => {
            response.json().then((data) => {
                $('#idEdit').val(data.id);
                $('#nameEdit').val(data.name);
                $('#emailEdit').val(data.email);
                $('#editInputPassword').val(data.password);
                var roles = data.roles;
                var newRoles = [];
                $('#newRoles option').each(function() {
                    newRoles[ $(this).val()] = $(this).val();
                });
                roles.forEach(function (item, i, roles) {
                    if(newRoles.includes(String(item.id))) {
                        $('#editFormControlSelect option[id='+item.id+']').prop('selected', true);
                    }
                })
            });
        });
}

function updateUser() {
    var roles = '?roles=';
    var newRoles = $('#editFormControlSelect').val();
    newRoles.forEach(function (item) {
        roles += item + ',';
    })

    var roles1 = roles.substr(0, roles.length - 1);
    var jsonVar = {
        id: document.getElementById("idEdit").value,
        name: document.getElementById("nameEdit").value,
        email: document.getElementById("emailEdit").value,
        password: document.getElementById("editInputPassword").value
    };

    var response = fetch('http://localhost:8080/admin/edit' + roles1, {
        method: 'PUT',
        body: JSON.stringify(jsonVar),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function () { document.location.reload() });

        $("#usersTable > tbody").empty();
        printUsers();
}

var userId;