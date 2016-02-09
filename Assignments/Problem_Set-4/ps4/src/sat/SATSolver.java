package sat;

import immutable.EmptyImList;
import immutable.ImList;
import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
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
		ImList<Clause> clauses = formula.getClauses();
		return solve (clauses, new Environment());
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
    private static Environment solve(ImList<Clause> clauses, Environment env) {
        if (clauses.isEmpty()) { // no clauses, trivially satisfiable
        	return env;
        } else {
        	Clause sc = null;
        	for (Clause c : clauses) {
        		if (c.isEmpty()) {
        			// contains empty clause, unsatisfiable, fail and backtrack
        			return null;
        		}
        		// find the smallest clause (by number of literals)
        		if ((sc == null) || (c.size() < sc.size())) {
        			sc = c;
        		}
        	}
        	Literal l = sc.chooseLiteral();
        	Variable var = l.getVariable();
        	if (sc.isUnit()) { // encounter a unit clause, apply unit propagation
        		// bind the literal's variable in the environment so that
        		// the clause is satisfied
        		if (l.equals(PosLiteral.make(l.getVariable()))) {
        			env = env.put(var, Bool.TRUE);
        		} else {
        			env = env.put(var, Bool.FALSE);
        		}
        		return solve(substitute(clauses, l), env);
        	} else { // pick an arbitrary literal from this small clause
        		if (l.equals(NegLiteral.make(l.getVariable()))) {
        			l = l.getNegation();
        		}
        		// try setting the literal to TRUE, substitute, then
        		// solve() recursively
        		Environment posTrial = solve(substitute(clauses, l), env.put(var, Bool.TRUE));
        		if (posTrial != null) { // the trial succeeds
        			return posTrial;
        		} else {
        			// the trial fails, then try setting the literal to FALSE,
        			// substitute, and solve recursively
        			l = l.getNegation();
        			return solve(substitute(clauses, l), env.put(var, Bool.FALSE));
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
        ImList<Clause> reducedClauses = new EmptyImList<Clause>();
        for (Clause c : clauses) {
        	Clause rc = c.reduce(l);
        	if (rc != null) {
        		reducedClauses = reducedClauses.add(rc);
        	}
        }
        return reducedClauses;
    }
}
