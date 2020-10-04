$(document).ready (getUser());

function getUser() {
    var url = 'http://localhost:8080/user';
    fetch(url)
        .then((response) => {
            response.json().then((data) => {
                var roles = data.roles;
                var stringRoles = '';
                roles.forEach(function (item) {
                    stringRoles += item.role + ' ';
                })
                var html = '<tr>' +
                    '<td>' + data.id + '</td>' +
                    '<td>' + data.name + '</td>' +
                    '<td>' + data.email + '</td>' +
                    '<td>' + stringRoles + '</td>' +
                    '</tr>';
                $('#userInfo').append(html);
            })
        })
}