package sat;

import static org.junit.Assert.*;

import org.junit.Test;

import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.PosLiteral;

public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }

    @Test
    public void testSolve001() {
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Literal la = PosLiteral.make(a);
    	Literal lb = PosLiteral.make(b);
    	Literal nlb = lb.getNegation();
    	Clause c1 = makeClause(la, nlb);
    	Clause c2 = makeClause(la, lb);
    	Formula f = makeFormula(c1, c2);
    	Environment env = SATSolver.solve(f);
    	assertEquals(env.get(a), Bool.TRUE);
    	assertEquals(env.get(b), Bool.TRUE);
    }
    
    @Test
    public void testSolve002() {
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Literal la = PosLiteral.make(a);
    	Literal lb = PosLiteral.make(b);
    	Literal lnb = lb.getNegation();
    	Clause cla = makeClause(la);
    	Clause clb = makeClause(lb);
    	Clause clnb = makeClause(lnb);
    	Formula f1 = makeFormula(cla, clb);
    	Formula f2 = makeFormula(cla, clnb);
    	Environment env = SATSolver.solve(f1.and(f2));
    	assertNull(env);
    }
    
    // (a | b) and (!b | c)
    @Test
    public void testSolve003() {
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	Literal la = PosLiteral.make(a);
    	Literal lb = PosLiteral.make(b);
    	Literal lnb = lb.getNegation();
    	Literal lc = PosLiteral.make(c);
    	Clause cla = makeClause(la);
    	Clause clb = makeClause(lb);
    	Clause clnbc = makeClause(lnb, lc);
    	Formula f1 = makeFormula(cla, clb);
    	Formula f2 = makeFormula(clnbc);
    	Environment env = SATSolver.solve(f1.and(f2));
    	assertEquals(env.get(a), Bool.TRUE);
    	assertEquals(env.get(b), Bool.TRUE);
    	assertEquals(env.get(c), Bool.TRUE);
    }
    
    @Test
    public void testSolve004() {
    	Variable a = new Variable("a");
    	Literal la = PosLiteral.make(a);
    	Literal lna = la.getNegation();
    	Clause cla = makeClause(la);
    	Clause clna = makeClause(lna);
    	Formula f = makeFormula(cla, clna);
    	Environment env = SATSolver.solve(f);
    	assertNull(env);
    }
    
    @Test
    public void testSolve005() {
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	Literal la = PosLiteral.make(a);
    	Literal lb = PosLiteral.make(b);
    	Literal lnb = lb.getNegation();
    	Literal lc = PosLiteral.make(c);
    	Clause c1 = makeClause(la, lnb);
    	Clause c2 = makeClause(la, lc);
    	Formula f = makeFormula(c1, c2);
    	Environment env = SATSolver.solve(f);
    	assertEquals(env.get(a), Bool.TRUE);
    	assertEquals(env.get(b), Bool.TRUE);
    	assertEquals(env.get(c), Bool.TRUE);
    }
    
    // Helper function for constructing a formula.  Takes
    // a variable number of arguments, e.g.
    // makeFormula(a, b, c) will make the formula (a and b and c)
    // @param e,...   clause in the formula
    // @return formula containing e,...
    private Formula makeFormula(Clause... e) {
        Formula f = new Formula();
        for (int i = 0; i < e.length; ++i) {
            f = f.addClause(e[i]);
        }
        return f;
    }
    
    // Helper function for constructing a clause.  Takes
    // a variable number of arguments, e.g.
    // make(a, b, c) will make the clause (a or b or c)
    // @param e,...   literals in the clause
    // @return clause containing e,...
    private Clause makeClause(Literal... e) {
        Clause c = new Clause();
        for (int i = 0; i < e.length; ++i) {
            c = c.add(e[i]);
        }
        return c;
    }
}