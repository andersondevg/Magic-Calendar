package br.edu.ifpb.si.pweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Usuario extends Pessoa {
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval=true)
	private List<CalendarComment> listComment;
	
	
	public Usuario(String nome, String senha){
		super(nome, senha);
		this.listComment = new ArrayList<CalendarComment>();
	}
	
	public Usuario(){}
	
	public void addComment(CalendarComment comment){
		//comment.setUsuario(this);
		this.listComment.add(comment);
	}
	
	public void delComment(CalendarComment comment){
		this.listComment.remove(comment);
	}
	
	public ArrayList<CalendarComment> getAllListComment(){
		return (ArrayList<CalendarComment>) this.listComment;
	}
	
	public CalendarComment getComment(int id, int type){
		for(CalendarComment comment : listComment){
			if(comment.getId() == id && comment.getType() == type ){
				return comment;
			}
		}
		return null;
	}
}