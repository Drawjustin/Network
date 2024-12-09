package was.v7.custom;

import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;

public class CustomController {
    @ClassNameAnno(name = "/site1")
    public void site1(HttpRequest request, HttpResponse response){
        response.writeBody("<h1>site1</h1>");
    }
    @ClassNameAnno(name = "/site2")
    public void site2(HttpRequest request, HttpResponse response){
        response.writeBody("<h1>site2</h1>");
    }

    @ClassNameAnno(name = "/search")
    public void search(HttpRequest request, HttpResponse response){
        String query = request.getParameter("q");
        response.writeBody("<h1>Search</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li>query: " + query + "</li>");
        response.writeBody("</ul>");
    }
    @ClassNameAnno(name = "/")
    public void home(HttpRequest request, HttpResponse response){
        response.writeBody("<h1>home</h1>");
        response.writeBody("<ul>");
        response.writeBody("<li><a href='/site1'>site1</a></li>");
        response.writeBody("<li><a href='/site2'>site2</a></li>");
        response.writeBody("<li><a href='/search?q=hello'>검색</a></li>");
        response.writeBody("</ul>");
    }

    @ClassNameAnno(name = "/favicon.ico")
    public void favicon(HttpRequest request, HttpResponse response){
        return;
    }
}
