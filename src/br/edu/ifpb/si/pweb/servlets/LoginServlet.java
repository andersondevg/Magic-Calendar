package br.edu.ifpb.si.pweb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.si.pweb.dao.DAO;
import br.edu.ifpb.si.pweb.dao.PessoaDAO;
import br.edu.ifpb.si.pweb.model.Pessoa;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = null;
		String pass = null;
		if(!request.getParameter("name").isEmpty() && !request.getParameter("password").isEmpty()){
			name = request.getParameter("name").toString();
			pass = request.getParameter("password").toString();
		}
		
		DAO.open();
			PessoaDAO pDAO = new PessoaDAO();
			Pessoa p = pDAO.readUser(name, pass);
			HttpSession session = request.getSession();
			session.setAttribute("logado", p);
		DAO.close();
		
		if(p != null)
			response.sendRedirect("painel.jsp");
		
	}

}