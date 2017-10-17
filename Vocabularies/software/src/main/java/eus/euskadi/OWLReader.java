/**
 * 
 */
package eus.euskadi;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

/**
 * @author megana
 *
 */
public class OWLReader {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException 
	 */
	public static void main(String[] args) throws OWLOntologyCreationException {
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File("../Euskadipedia/ref.owl"));
		Set<OWLNamedIndividual> individuals = ontology.getIndividualsInSignature();
		Iterator<OWLNamedIndividual> individualsIterator = individuals.iterator();
		System.out.println("Individuos de " + ontology.getOntologyID().getOntologyIRI());
		while(individualsIterator.hasNext()){
			System.out.println(individualsIterator.next().getIRI());
		}
	}
}
