<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<html>
    <head>
        <title>Upload Test</title>
    </head>
    <body>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <label>file type<input type="text" name="fType"></label><br>
            <label>file params<input type="text" name="fParams"></label><label>(comma separated params)</label><br>
            <label>attach file<input type="file" name="myFile"></label><br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>