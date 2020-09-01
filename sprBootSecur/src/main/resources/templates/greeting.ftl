<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<div>Hello, user</div>
<@l.logout />
<a href="/user">to User list page</a>
</@c.page>