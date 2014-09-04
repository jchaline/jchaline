<table>
    <tr><th>Name</th><th>First day</th></tr>
    <g:each in="${customerList}" var="cust">
        <tr><td>${cust.lastName}, ${cust.firstName}</td><td>${cust.firstDay}</td></tr>
    </g:each>
</table>