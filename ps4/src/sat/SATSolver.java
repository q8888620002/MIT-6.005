package sat;

import java.util.Iterator;

import immutable.EmptyImList;
import immutable.ImList;
import sat.env.Bool;
import sat.env.Environment;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
import sat.formula.PosLiteral;

/**
 * A simple DPLL SAT solver. See http://en.wikipedia.org/wiki/DPLL_algorithm
 */
public class SATSolver {

	/**
     * Solve the problem using a simple version of DPLL with backtracking and
     * unit propagation. The returned environment binds literals of class
     * bool.Variable rather than the special literals used in clausification of
     * class clausal.Literal, so that clients can more readily use it.
     * 
     * @return an environment for which the problem evaluates to Bool.TRUE, or
     *         null if no such environment exists.
     */
    public static Environment solve(Formula formula) {
        // TODO: implement this.
    	Environment environment = new Environment();
    	return solve(formula.getClauses(),environment);

    }

    /**
     * Takes a partial assignment of variables to values, and recursively
     * searches for a complete satisfying assignment.
     * 
     * @param clauses
     *            formula in conjunctive normal form
     * @param env
     *            assignment of some or all variables in clauses to true or
     *            false values.
     * @return an environment for which all the clauses evaluate to Bool.TRUE,
     *         or null if no such environment exists.
     */
    private static Environment solve(ImList<Clause> clauses, Environment e) {
        // TODO: implement this.
    	if(clauses.size() == 0){
    		return e;
    	}else{
    		Iterator<Clause> cIter = clauses.iterator();
    		Clause c = null;
    		while(cIter.hasNext()){
    			Clause clause = cIter.next();
    			if(clause.isEmpty()){
    				return null;
    			}else{
    				if((c == null) || (c.size() > clause.size())){
    					c = clause;
    				}
    			}
    		}
    			if(c.isUnit()){
    				// unit propagation
    				Literal l = c.chooseLiteral();
    				// the clause is satisfied
            		if (l.equals(PosLiteral.make(l.getVariable()))) {
            			e = e.put(l.getVariable(), Bool.TRUE);
            		} else {
            			e = e.put(l.getVariable(), Bool.FALSE);
            		}
    				
    				ImList<Clause> subClause = substitute(clauses, l);
    				
    				return solve(subClause, e);
    			}else{
    				Literal l = c.chooseLiteral();
    				if(l.equals(NegLiteral.make(l.getVariable()))){
    					l = l.getNegation();
    				}
    				
    				Environment posTrial = solve(substitute(clauses, l), e.putTrue(l.getVariable()));
    				
    				if(posTrial != null){
    					return posTrial;
    				}else{
    					return  solve(substitute(clauses, l), e.putFalse(l.getVariable()));
    				}
    			}
    	}
    }

    /**
     * given a clause list and literal, produce a new list resulting from
     * setting that literal to true
     * 
     * @param clauses
     *            , a list of clauses
     * @param l
     *            , a literal to set to true
     * @return a new list of clauses resulting from setting l to true
     */
    private static ImList<Clause> substitute(ImList<Clause> clauses,
            Literal l) {
        // TODO: implement this.
    	Iterator<Clause> cIterator = clauses.iterator();
    	ImList<Clause> cList = new EmptyImList<Clause>();
    
    	while (cIterator.hasNext()){
    		Clause c = cIterator.next();
    		c = c.reduce(l);
    		if(c != null ){	
    				cList = cList.add(c);
    		}
    	}
		return cList;
    }

}
