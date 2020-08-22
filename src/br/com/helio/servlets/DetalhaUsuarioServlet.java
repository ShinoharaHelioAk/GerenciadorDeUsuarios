package br.com.helio.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helio.dao.UsuarioDAO;
import br.com.helio.model.Usuario;

@WebServlet(name = "detalhaUsuarioServlet", urlPatterns = "/detalhaUsuario")
public class DetalhaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -2995645272024881607L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idRequestParam = req.getParameter("id");
		//System.out.println(idRequestParam);
		Long id = Long.parseLong(idRequestParam);
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
		PrintWriter out = resp.getWriter();
		//out.println("Testando o Servlet " + getServletName() + ".");
		//out.println("Passando o ID " + id + ".");
		
		out.println("<html>");
		out.println("<head><title>SGUSR - Confirma Exclusão de Usuário</title></head>");
		out.println("<body>");
		out.println("<h3>Confirmação de Exclusão de Usuário</h3>");
		
		//Buscar na base os dados do usuário usando o id
		usuario = dao.getUserById(id);
		
		
		//Popular os campos da Servlet
		out.println("<table border=\"1\">");
		out.println("<tr><td>Nome: </td><td>" + usuario.getNome() + "</td></tr>");
		
		out.println("<tr><td>E-mail: </td><td>");
		if (usuario.getEmail() == null)
			out.println("");
		else
			out.println(usuario.getEmail());
		out.println("</td></tr>");
		
		out.println("<tr><td>Data de Nascimento: </td><td>");
		if (usuario.getDataNascimento() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy").format(usuario.getDataNascimento().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Status: </td><td>" + usuario.getStatus() + "</td></tr>");
		
		out.println("<tr><td>Data de Inclusão: </td><td>" + new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").
				format(usuario.getDataInclusao().getTime()) + "</td></tr>");
		
		out.println("<tr><td>Última Modificação: </td><td>");
		if (usuario.getDataModificacao() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").format(usuario.getDataModificacao().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Descrição da Última Modificação: </td><td>");
		if (usuario.getMotivoAlteracao() == null)
			out.println("");
		else
			out.println(usuario.getMotivoAlteracao());
		out.println("</td></tr>");
		
		out.println("</table>");
		out.println("</br></br>");
		out.println("<p>Deseja excluir esse usuário?</p>");
		out.println("<a href=\"removeUsuario?id=" + usuario.getId() + "\">Sim</a>");
		//out.println("<script>");
		//out.println("function confirmaExclusao() {");
		//out.println("var txt;");
		//out.println("if (confirm(\"Deseja excluir esse usuário?\")) {");
		//out.println("<p>Clicou no botão OK! Excluindo usuário da base de dados!</p>");
		//out.println("} else {");
		//out.println("<p>Clicou no botão Cancelar! Não faz nada!</p>");
		//out.println("}");
		//out.println("}");
		//out.println("window.confirm(\"Deseja excluir esse usuário?\");");
		//out.println("</script>");
		out.println("</br></br>");
		out.println("<a href=\"listaUsuarios\">Voltar para a Lista de Usuários</a>");
		out.println("<a href=\"index.html\">Voltar para a Home</a>");
		out.println("</body>");
		out.println("</html>");
		
		out.close();
	}

}
