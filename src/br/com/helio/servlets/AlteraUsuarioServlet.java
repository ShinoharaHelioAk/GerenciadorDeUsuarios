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

@WebServlet(name = "alteraUsuarioServlet", urlPatterns = "/alteraUsuario")
public class AlteraUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = -1166080294129123803L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("id");
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
		usuario.setId(Long.parseLong(id));
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setDataNascimento(dataNascimento);
		usuario.setDataModificacao(Calendar.getInstance());
		usuario.setMotivoAlteracao("Alteração de Dados do Usuário");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.alteraDadosUsuario(usuario);
		
		Usuario usuarioLista = new Usuario();
		usuarioLista = dao.getUserById(usuario.getId());
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>SGUSR - Sistema de Gerenciamento de Usuários</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Dados do Usuário alterados com sucesso!</h3>");
		
		out.println("<table border=\"1\">");
		out.println("<tr><td>Nome: </td><td>" + usuarioLista.getNome() + "</td></tr>");
		
		out.println("<tr><td>E-mail: </td><td>");
		if (usuarioLista.getEmail() == null)
			out.println("");
		else
			out.println(usuarioLista.getEmail());
		out.println("</td></tr>");
		
		out.println("<tr><td>Data de Nascimento: </td><td>");
		if (usuarioLista.getDataNascimento() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy").format(usuarioLista.getDataNascimento().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Status: </td><td>" + usuarioLista.getStatus() + "</td></tr>");
		
		out.println("<tr><td>Data de Inclusão: </td><td>" + new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").
				format(usuarioLista.getDataInclusao().getTime()) + "</td></tr>");
		
		out.println("<tr><td>Última Modificação: </td><td>");
		if (usuarioLista.getDataModificacao() == null)
			out.println("");
		else
			out.println(new SimpleDateFormat("dd/MM/yyyy HH:mm:sss").format(usuarioLista.getDataModificacao().getTime()));
		out.println("</td></tr>");
		
		out.println("<tr><td>Descrição da Última Modificação: </td><td>");
		if (usuarioLista.getMotivoAlteracao() == null)
			out.println("");
		else
			out.println(usuarioLista.getMotivoAlteracao());
		out.println("</td></tr>");
		
		out.println("</table>");
		out.println("</br></br>");
		
		
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