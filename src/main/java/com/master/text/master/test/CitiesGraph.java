package com.master.text.master.test;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.graph.ElementOrder;
import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.Graphs;
import com.google.common.graph.ImmutableGraph.Builder;
import com.google.common.graph.Traverser;

/**
 * Graph is the data structure selected to compute reach ability between any 2 given cities
 * @author maneesh
 *
 */
public class CitiesGraph {
	
	/**
	 * Graph Builder to create a graph that is undirected			 
	 */
	public static Builder<String> builder = GraphBuilder.undirected()
                .nodeOrder(ElementOrder.insertion())
                .<String>immutable();
	
	/**
	 * Selected data structure is a graph
	 */
	public static Graph<String> cityGraph = null;
	
	/**
	 * Adds given cities to the graph and creates edges
	 * @param sourceCity
	 * @param destinationCity
	 */
	private static void addToGraph(String sourceCity, String destinationCity) {
		builder.putEdge(sourceCity, destinationCity);
	}
	/**
	 * Builds the graph once all the cities and edges are added
	 */
	private static void buildGraph() {
		cityGraph = builder.build();		
	}
	
	/**
	 * Checks if 2 given cities are connected , By checking the undirected graph
	 * Should the graph be empty , a graph will be built with the input file
	 * @param sourceCity
	 * @param destinationCity
	 * @return
	 */
	public static boolean checkSourceToDestination(String sourceCity, String destinationCity) {
		Boolean destinationReachable = false;
		
		try {
			if(cityGraph == null ){
				CitiesGraph.readFileToNamesList();
			}

			Set<String> reachableNodes =  Graphs.reachableNodes(cityGraph, sourceCity);
			
			
			System.out.println("reachable nodes from node "+sourceCity);
			destinationReachable = reachableNodes.stream().anyMatch(reachableNode->{
				return reachableNode.equalsIgnoreCase(destinationCity);
				});
			
			return destinationReachable;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return destinationReachable;
	}
	
	/**
	 * Reads the input file of city links and creates the graph
	 */
	private static void  readFileToNamesList() {
		String sourceCity;
		String destinationCity;
		System.out.println("Reading Source File");
		try{
			Scanner sc = new Scanner(new File(CitiesGraph.class.getResource("./city.txt").getPath()));  
			sc.useDelimiter("\n");   //sets the delimiter pattern  
			while (sc.hasNext())    
			{  
				String cityLinks = sc.next();
				if(StringUtils.isNotEmpty(cityLinks)){
					System.out.println("Input line - "+cityLinks);
					String cityLinksArr[] = cityLinks.split(",");
					sourceCity = cityLinksArr[0];
					destinationCity = cityLinksArr[1];
					if(StringUtils.isNotEmpty(sourceCity) && StringUtils.isNotEmpty(destinationCity)){
						sourceCity=sourceCity.trim();
						destinationCity=destinationCity.trim();
						System.out.println("\t"+"Source City - "+sourceCity+" Destination City - "+destinationCity);
						
						//Now add to graph
						addToGraph(sourceCity,destinationCity);
					}
				}
			}
			buildGraph();
			sc.close(); 
		}catch(Exception e){
			System.out.println("Could not read file");
			e.printStackTrace();
		}

		System.out.println("Here");
	}

	public static void main(String[] args) {
		CitiesGraph demo = new CitiesGraph();
		//demo.graphTest();
		
		CitiesGraph.readFileToNamesList();
        Traverser.forGraph(cityGraph).depthFirstPostOrder("Boston")
        .forEach(x->System.out.println(x));
        
        
        boolean sourceToDestination = checkSourceToDestination("NewYork","Albany");
        System.out.println(sourceToDestination==true?"yes":"no");
        

		
	}

	
}
