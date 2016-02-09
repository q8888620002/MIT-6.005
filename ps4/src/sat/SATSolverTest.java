package sat;

import static org.junit.Assert.*;

import org.junit.Test;

import sat.env.Bool;
import sat.env.Environment;
import sat.env.Variable;
import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.NegLiteral;
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
	public void test_empty_solution() {
		Formula f = new Formula();
		assertNull(SATSolver.solve(f));
	}
    
    
    @Test 
    public void emptyClauseTest(){
    	Clause clause = new Clause();
    	Formula formula = new Formula(clause);
    	assertNull(SATSolver.solve(formula));
    }

    // TODO: put your test cases here

    // Test (a|b) and (a|c) , result should a: true, b:true, c: true 
    @Test
    public void solverTest01(){
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	
    	Literal posA = PosLiteral.make(a);
    	Literal posB = PosLiteral.make(b);
    	Literal posC = PosLiteral.make(c);
    	Clause aOrb = makeClause(posA,posB);
    	Clause aOrC = makeClause(posA,posC);
    	
    	Formula formula = new Formula(aOrb);
    	Formula f = new Formula(aOrC);
    	Formula fAndF = f.and(formula);
    	
    	sat.env.Environment e = SATSolver.solve(fAndF);
    	System.out.println(fAndF.toString());
    	assertEquals(e.get(a), Bool.TRUE);
    	assertEquals(e.get(b), Bool.TRUE);
    	assertEquals(e.get(c), Bool.TRUE);
    }
    
    // (a | !b) & (a | b) should return a: True, b: anything
    @Test
    public void solverTest02(){
    	Variable a = new Variable("a");
    	
    	Literal posA = PosLiteral.make(new Variable("a"));
    	Literal posB = PosLiteral.make(new Variable("b"));
    	Literal negB = posB.getNegation();
   
    	Clause aOrb = makeClause(posA,posB);
    	Clause aOrNotB = makeClause(posA,negB);
    	
    	Formula formula = new Formula(aOrb);
    	Formula f = new Formula(aOrNotB);
    	Formula fAndF = f.and(formula);
    	
    	sat.env.Environment e = SATSolver.solve(fAndF);
    	assertEquals(e.get(a), Bool.TRUE);
    	
    }
    
    // (a & !b) & (a & b) should return null
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
    	System.out.println(f1.and(f2).toString());
    	Environment env = SATSolver.solve(f1.and(f2));
    	
    	assertNull(env);
    }
    
    // (a & b) & (!b | c) should a: True, b: True, c: True
    @Test
    public void solverTest04(){
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable nB = new Variable("negB");
    	Variable c = new Variable("c");
    	
    	Literal posA = PosLiteral.make(a);
    	Literal posB = PosLiteral.make(b);
    	Literal negB = posB.getNegation();
    	Literal posC = PosLiteral.make(c);
    	
    	Clause aAndB = makeClause(posA,posB);
    	Clause bAndC = makeClause(negB,posC);
    	
    	Formula af = new Formula(aAndB);
    	Formula notBorC = new Formula(bAndC);
    	
    	
    	Formula fAndF = af.and(notBorC);
    	
    	sat.env.Environment e = SATSolver.solve(fAndF);
    	assertEquals(e.get(a), Bool.TRUE);
    	assertEquals(e.get(b), Bool.TRUE);
    	assertEquals(e.get(c), Bool.TRUE);
    	
    }
    
    // (a & b) & (a & c) should a: True, b: True, c: True
    @Test
    public void solverTest05(){
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	Literal posA = PosLiteral.make(a);
    	Literal posB = PosLiteral.make(b);
    	Literal posC = PosLiteral.make(c);
    	
    	
    	Clause aAndB = makeClause(posA,posB);
    	Clause aAndC = makeClause(posA,posC);
    	
    	Formula formula = new Formula(aAndB);
    	Formula f = new Formula(aAndC);
    	Formula fAndF = f.and(formula);
    	
    	sat.env.Environment e = SATSolver.solve(fAndF);
    	assertEquals(e.get(a), Bool.TRUE);
    	assertEquals(e.get(b), Bool.TRUE);
    	assertEquals(e.get(c), Bool.TRUE);
    	
    }
    
    // (a & !b) & (a & !c) should a: True, b: False, c: False
    @Test
    public void solverTest06(){
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	
    	Literal posA = PosLiteral.make(a);
    	Literal posB = PosLiteral.make(b);
    	Literal posC = PosLiteral.make(c);
    	Literal negB = posB.getNegation();
    	Literal negC = posC.getNegation();
    	
    	Clause cA = makeClause(posA);
    	Clause cB = makeClause(negB);
    	Clause cC = makeClause(negC);
    	
    	Formula formula = new Formula(cA);
    	formula = formula.addClause(cB);
    	formula =  formula.addClause(cC);
    	
    	
    	sat.env.Environment e = SATSolver.solve(formula);
    	assertEquals(e.get(a), Bool.TRUE);
    	assertEquals(e.get(b), Bool.FALSE);
    	assertEquals(e.get(c), Bool.FALSE);
    	
    }
    // (a & !a) return null 
    @Test 
   
    public void solverTest07(){
    	Variable a = new Variable("a");
    	Variable na = new Variable("na");
    	Literal posA = PosLiteral.make(a);
    	Literal negA = NegLiteral.make(na);
    	negA = posA.getNegation();
    	
    	Clause aClause = makeClause(posA);
    	Clause naClause = makeClause(negA);
    	
    	Formula f = new Formula(aClause);
    	Formula f2 = new Formula(naClause);
    	
    	assertNull(SATSolver.solve(f.and(f2)));
     }
    
    // Not test : !((a | b) & c ) return a:FALSE b: FALSE c: anything  
    //  			or c : false, a: anything,b : anything   
    @Test 
    
    public void solverTest08(){
    	Variable a = new Variable("a");
    	Variable b = new Variable("b");
    	Variable c = new Variable("c");
    	Literal posA = PosLiteral.make(a);
    	Literal posB = PosLiteral.make(b);
    	Literal posC = PosLiteral.make(c);
    	Literal negA = posA.getNegation();
    	Literal negB = posB.getNegation();
    
    	
    	Clause aOrB = makeClause(posA, posB);
    	Clause cc = makeClause(posC);
    	
    	Formula f = makeFormula(aOrB, cc);	
    	
    	Environment environment = SATSolver.solve(f.not());
    	
    	assertEquals(environment.get(a), Bool.FALSE);
    	assertEquals(environment.get(b), Bool.FALSE);
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