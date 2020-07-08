package it.polito.tdp.crimes.model;

import java.lang.ProcessHandle.Info;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<Distretto, DefaultWeightedEdge> grafo;
	private List<Distretto> distretti;
	private Integer N_DISTRETTI = 7;
	public Model() {
		dao = new EventsDao();
	}
	

	public List<Integer> getAnni() {
		return dao.getAnni();
	}


	public void creaGrafo(Integer anno) {
		this.grafo = new SimpleWeightedGraph<Distretto, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		this.distretti = new ArrayList<Distretto>();
		for(int i =1; i<=N_DISTRETTI; i++) {
			distretti.add(new Distretto(i, dao.getEventi(i, anno)));
		}
		
		for(Distretto d: distretti) {
			for(Distretto j: distretti)
				if(!d.equals(j)) {
					DefaultWeightedEdge e = this.grafo.getEdge(d, j);
					if(e==null) {
						Graphs.addEdgeWithVertices(this.grafo, d, j, LatLngTool.distance(d.getCenter(), j.getCenter(), LengthUnit.KILOMETER));
						System.out.format("#Vertice: %d #Vertice: %d\n", d.getDistrict_id(), j.getDistrict_id());

					}
				}
		}
		
		System.out.format("#Vertici: %d\n#Archi: %d", this.grafo.vertexSet().size(), this.grafo.edgeSet().size());
		
		
	}
	
	
	public List<InfoArco> getArchi(Distretto d){
		
		List<InfoArco> vicini = new ArrayList<>();
		
		for(Distretto ds: Graphs.neighborListOf(this.grafo, d)){
			
			vicini.add(new InfoArco(ds, this.grafo.getEdgeWeight(this.grafo.getEdge(d, ds))));
			
		}
		
		
		Collections.sort(vicini);
		
		return vicini;
		
	}
	
	public Set<Distretto> getVertexSet() {
		return this.grafo.vertexSet();
	}
	
}
