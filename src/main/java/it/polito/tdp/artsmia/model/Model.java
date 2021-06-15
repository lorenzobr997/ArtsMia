package it.polito.tdp.artsmia.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	
	private Graph<ArtObject, DefaultWeightedEdge> grafo;
	private ArtsmiaDAO dao;
	private Map<Integer, ArtObject> idMap;
	
	public Model() {
		dao = new ArtsmiaDAO();
		idMap = new HashMap<Integer, ArtObject>();
	}
	
	public void creaGrafo(){
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		dao.listObjects(idMap);
		Graphs.addAllVertices(grafo,idMap.values());
		
		for(Adiacenza a : dao.getAdiacenze()) {
			Graphs.addEdge(this.grafo, idMap.get(a.getId1()), 
				idMap.get(a.getId2()), a.getPeso());
		}
		System.out.println("GRAFO CREATO!");
		System.out.println("# VERTICI: " + grafo.vertexSet().size());
		System.out.println("# ARCHI: " + grafo.edgeSet().size());
	}
		
	public int getNVertici() {
		return this.grafo.vertexSet().size();
	}
		
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	}