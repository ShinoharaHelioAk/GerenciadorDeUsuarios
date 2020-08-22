package br.com.helio.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.helio.dao.UsuarioDAO;
import br.com.helio.model.Usuario;

@WebServlet(name = "adicionaUsuarioServlet", urlPatterns = "/adicionaUsuario")
public class AdicionaUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -5449336034846021795L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String dataNascimentoEmTexto = req.getParameter("dataNascimento");
		//log("dataNascimentoEmTexto: \"" + dataNascimentoEmTexto + "\"");
		//log("dataNascimentoEmTexto.length(): " + dataNascimentoEmTexto.length());
		Calendar dataNascimento = null;
		
		if (!dataNascimentoEmTexto.isEmpty()) {
			try {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataNascimentoEmTexto);
				dataNascimento = Calendar.getInstance();
				dataNascimento.setTime(date);
			} catch (ParseException e) {
				out.println("Erro na conversão da data.\n\n\n");
				//out.println("Detalhes do Erro: \n"+e);
				return;
			}
		}
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setDataNascimento(dataNascimento);
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.adicionaUsuario(usuario);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>SGUSR - Sistema de Gerenciamento de Usuários</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Usuário cadastrado com sucesso!</h3>");
		out.println("<form action=\"cadastraUsuarios.html\">");
		out.println("<input type=\"submit\" value=\"Cadastrar Novo Usuário\">");
		out.println("</form>");
		out.println("<form action=\"listaUsuarios\">");
		out.println("<input type=\"submit\" value=\"Listar Usuários\">");
		out.println("</form>");
		out.println("<a href=\"index.html\">Voltar</a>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}