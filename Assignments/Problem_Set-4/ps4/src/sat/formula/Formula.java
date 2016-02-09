/**
 * Author: dnj, Hank Huang
 * Date: March 7, 2009
 * 6.005 Elements of Software Construction
 * (c) 2007-2009, MIT 6.005 Staff
 */
package sat.formula;

import immutable.EmptyImList;
import immutable.ImList;
import immutable.NonEmptyImList;

import java.util.Iterator;

/**
 * Formula represents an immutable boolean formula in
 * conjunctive normal form, intended to be solved by a
 * SAT solver.
 * 
 * Datatype expression:
 * 		Formula = ImList<Clause>			// a list of clauses ANDed together
 * 		Clause	= ImList<Literal>			// a list of literals ORed together
 * 		Literal = PosLiteral(val: String) + NegLiteral(val: String)
 */
public class Formula {
    private final ImList<Clause> clauses;
    // Rep invariant:
    //      clauses != null
    //      clauses contains no null elements (ensured by spec of ImList)
    //
    // Note: although a formula is intended to be a set,  
    // the list may include duplicate clauses without any problems. 
    // The cost of ensuring that the list has no duplicates is not worth paying.
    //
    //    
    //    Abstraction function:
    //        The list of clauses c1,c2,...,cn represents 
    //        the boolean formula (c1 and c2 and ... and cn)
    //        
    //        For example, if the list contains the two clauses (a,b) and (!c,d), then the
    //        corresponding formula is (a or b) and (!c or d).

    void checkRep() {
        assert this.clauses != null : "SATProblem, Rep invariant: clauses non-null";
    }
    
    private Formula(ImList<Clause> clauses) {
        this.clauses = clauses;
        checkRep();
    }

    /**
     * Create a new problem for solving that contains no clauses (that is the
     * vacuously true problem)
     * 
     * @return the true problem
     */
    public Formula() {
    	this.clauses = new EmptyImList<Clause> ();
    	checkRep();
    }

    /**
     * Create a new problem for solving that contains a single clause with a
     * single literal
     * 
     * @return the problem with a single clause containing the literal l
     */
    public Formula(Literal l) {
    	Clause c = new Clause(l);
    	this.clauses = new NonEmptyImList<Clause>(c);
    	checkRep();
    }

    /**
     * Create a new problem for solving that contains a single clause
     * 
     * @return the problem with a single clause c
     */
    public Formula(Clause c) {
    	clauses = new NonEmptyImList<Clause>(c);
    	checkRep();
    }
    
    /**
     * Add a clause to this problem
     * 
     * @return a new problem with the clauses of this, but c added
     */
    public Formula addClause(Clause c) {
        return new Formula(this.clauses.add(c));
    }

    /**
     * Get the clauses of the formula.
     * 
     * @return list of clauses
     */
    public ImList<Clause> getClauses() {
        return this.clauses;
    }

    /**
     * Iterator over clauses
     * 
     * @return an iterator that yields each clause of this in some arbitrary
     *         order
     */
    public Iterator<Clause> iterator() {
        return clauses.iterator();
    }

    /**
     * @return a new problem corresponding to the conjunction of this and p
     */
    public Formula and(Formula p) {
        Formula cof = this;		// conjunction of two formulas
        // iterator for clauses in formula p
        Iterator<Clause> pIter = p.iterator();
        while (pIter.hasNext()) {
        	cof = cof.addClause(pIter.next());
        }
        return cof;
    }

    /**
     * @return a new problem corresponding to the disjunction of this and p
     */
    public Formula or(Formula p) {
        // Hint: you'll need to use the distributive law to preserve conjunctive normal form, i.e.:
        //   to do (a & b) .or (c & d),
        //   you'll need to make (a | c) & (b | c) & (a | d) & (b | d)        
        Formula dof = new Formula();	// disjunction of two formulas
        if (this.getClauses().isEmpty()) {
        	dof = p;
        } else if (p.getClauses().isEmpty()) {
        	dof = this;
        } else {
        	// iterator for clauses in formula p
        	Iterator<Clause> pIter = p.iterator();
        	while (pIter.hasNext()) {
        		Clause pc = pIter.next();
        		// obtain the disjunction of this formula and pc,
        		// which is a formula
        		Formula f = getFCDisjunct(this, pc);
        		dof = dof.and(f);
        	}
        }
        return dof;
    }
    
    // Return the disjunction of a formula f and a clause c, i.e.:
    //   getFCDisj((a & b), c) => (a | c) & (b | c)
    // Requires: f is not an empty formula
    private Formula getFCDisjunct(Formula f, Clause c) {
    	Formula result = new Formula();
    	// iterator for clauses in formula f
		Iterator<Clause> fIter = f.iterator();
		while (fIter.hasNext()) {
			Clause clause = fIter.next().merge(c);
			result = result.addClause(clause);
		}
    	return result;
    }

    /**
     * @return a new problem corresponding to the negation of this
     */
    public Formula not() {
        // Hint: you'll need to apply DeMorgan's Laws (http://en.wikipedia.org/wiki/De_Morgan's_laws)
        // to move the negation down to the literals, and the distributive law to preserve 
        // conjunctive normal form, i.e.:
        //   if you start with (a | b) & c,
        //   you'll need to make !((a | b) & c) 
        //                       => (!a & !b) | !c            (moving negation down to the literals)
        //                       => (!a | !c) & (!b | !c)    (conjunctive normal form)
    	Formula result = new Formula();
    	Iterator<Clause> tIter = this.iterator();
    	while (tIter.hasNext()) {
    		Clause c = tIter.next();
    		Formula f = negateClause(c);
    		result = result.or(f);
    	}
    	return result;
    }
    
    // Return the negation of a clause, which by DeMorgan's Laws, is a formula
    private Formula negateClause(Clause c) {
    	Formula f = new Formula();
    	Iterator<Literal> cIter = c.iterator();
    	while (cIter.hasNext()) {
    		Literal l = cIter.next();
    		Literal nl = l.getNegation();
    		f = f.addClause(new Clause(nl));
    	}
    	return f;
    }

    /**
     * 
     * @return number of clauses in this
     */
    public int getSize() {
        return clauses.size();
    }

    /**
     * @return string representation of this formula
     */
    public String toString() {
        String result = "Problem[";
        for (Clause c : clauses)
            result += "\n" + c;
        return result + "]";
    }
}
