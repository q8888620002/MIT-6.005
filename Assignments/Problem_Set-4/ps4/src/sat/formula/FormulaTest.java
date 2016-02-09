package sat.formula;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class FormulaTest {
	// helpful values for test cases
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");

    // make sure assertions are turned on!  
    // we don't want to run test cases without assertions too.
    // see the handout to find out how to turn them on.
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    
    // test the constructor of Formula for no clause
    @Test
    public void testNoClauseFormula() {
    	Formula ef = new Formula();		// an empty formula
    	assertEquals(ef.getSize(), 0);
    	assertTrue(ef.getClauses().isEmpty());
    }
    
    // test the constructor of Formula for a single literal <blank>
    @Test
    public void testSingleLiteralFormula001() {
    	Literal l = PosLiteral.make("");	// a literal
    	Formula f = new Formula(l);		// a non-empty formula	
    	assertEquals(f.getSize(), 1);
    	assertTrue(f.getClauses().first().isUnit());
    	assertEquals(f.getClauses().first().chooseLiteral(), l);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    // test the constructor of Formula for a single literal e
    @Test
    public void testSingleLiteralFormula002() {
    	Literal l = PosLiteral.make("e");
    	Formula f = new Formula(l);
    	assertEquals(f.getSize(), 1);
    	assertTrue(f.getClauses().first().isUnit());
    	assertEquals(f.getClauses().first().chooseLiteral(), l);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    // test the constructor of Formula for a single literal el
    @Test
    public void testSingleLiteralFormula003() {
    	Literal l = PosLiteral.make("el");
    	Formula f = new Formula(l);
    	assertEquals(f.getSize(), 1);
    	assertTrue(f.getClauses().first().isUnit());
    	assertEquals(f.getClauses().first().chooseLiteral(), l);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    // test the constructor of Formula for a single clause with
    // a single literal
    @Test
    public void testSingleClauseFormula001() {
    	Literal l = PosLiteral.make("l");
    	Clause c = makeClause(l);
    	Formula f = new Formula(c);
    	assertEquals(f.getSize(), 1);
    	assertEquals(f.getClauses().first(), c);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    // test the constructor of Formula for a single clause with
    // two literals
    @Test
    public void testSingleClauseFormula002() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause c = makeClause(l1, l2);
    	Formula f = new Formula(c);
    	assertEquals(f.getSize(), 1);
    	assertEquals(f.getClauses().first(), c);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testAddClause001() {
    	Formula ef = new Formula();		// an empty formula
    	Clause ec = makeClause();				// an empty clause
    	assertEquals(ef.getSize(), 0);
    	assertTrue(ef.getClauses().isEmpty());
    	
    	// add an empty clause to the empty formula
    	Formula secf = ef.addClause(ec);	// a single empty clause formula
    	assertEquals(secf.getSize(), 1);
    	assertEquals(secf.getClauses().first(), ec);
    	assertTrue(secf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testAddClause002() {
    	Formula ef = new Formula();
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause nec = makeClause(l1, l2);			// a non-empty clause
    	assertEquals(ef.getSize(), 0);
    	assertTrue(ef.getClauses().isEmpty());
    	
    	// add an non-empty clause to the empty formula
    	Formula snecf = ef.addClause(nec);
    	assertEquals(snecf.getSize(), 1);
    	assertEquals(snecf.getClauses().first(), nec);
    	assertTrue(snecf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testAddClause003() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause nec = makeClause(l1, l2);
    	Formula snecf = new Formula(nec);
    	assertEquals(snecf.getSize(), 1);
    	assertEquals(snecf.getClauses().first(), nec);
    	assertTrue(snecf.getClauses().rest().isEmpty());
    	
    	// add an empty clause to the single non-empty clause
    	// formula
    	Clause ec = makeClause();
    	Formula tcf = snecf.addClause(ec);	// a two-clause formula
    	assertEquals(tcf.getSize(), 2);
    	assertEquals(tcf.getClauses().first(), ec);
    	assertEquals(tcf.getClauses().rest().first(), nec);
    	assertTrue(tcf.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testAddClause004() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause nec1 = makeClause(l1, l2);
    	Formula snecf = new Formula(nec1);
    	assertEquals(snecf.getSize(), 1);
    	assertEquals(snecf.getClauses().first(), nec1);
    	assertTrue(snecf.getClauses().rest().isEmpty());
    	
    	// add an non-empty clause to the single non-empty clause
    	// formula
    	Literal l3 = PosLiteral.make("l3");
    	Literal l4 = PosLiteral.make("l4");
    	Clause nec2 = makeClause(l3, l4);
    	Formula tcf = snecf.addClause(nec2);
    	assertEquals(tcf.getSize(), 2);
    	assertEquals(tcf.getClauses().first(), nec2);
    	assertEquals(tcf.getClauses().rest().first(), nec1);
    	assertTrue(tcf.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testGetClauses001() {
    	Formula ef = new Formula();
    	assertEquals(ef.getSize(), 0);
    	assertTrue(ef.getClauses().isEmpty());
    }
    
    @Test
    public void testGetClauses002() {
    	Literal l = PosLiteral.make("l");
    	Clause c = makeClause(l);
    	Formula f = new Formula(c);
    	assertEquals(f.getSize(), 1);
    	assertEquals(f.getClauses().first(), c);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testGetClauses003() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause c1 = makeClause(l1, l2);
    	Formula snecf = new Formula(c1);
    	assertEquals(snecf.getSize(), 1);
    	assertEquals(snecf.getClauses().first(), c1);
    	assertTrue(snecf.getClauses().rest().isEmpty());
    	
    	// add an non-empty clause to the single non-empty clause
    	// formula
    	Literal l3 = PosLiteral.make("l3");
    	Literal l4 = PosLiteral.make("l4");
    	Clause c2 = makeClause(l3, l4);
    	Formula tcf = snecf.addClause(c2);
    	assertEquals(tcf.getSize(), 2);
    	assertEquals(tcf.getClauses().first(), c2);
    	assertEquals(tcf.getClauses().rest().first(), c1);
    	assertTrue(tcf.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testIterator001() {
    	Formula ef = new Formula();
    	Iterator<Clause> iter = ef.iterator();
    	assertFalse(iter.hasNext());
    }
    
    @Test
    public void testIterator002() {
    	Literal l = PosLiteral.make("l");
    	Clause c = makeClause(l);
    	Formula f = new Formula(c);
    	Iterator<Clause> iter = f.iterator();
    	assertTrue(iter.hasNext());
    	assertEquals(iter.next(), c);
    	assertFalse(iter.hasNext());
    }
    
    @Test
    public void testIterator003() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Literal l3 = PosLiteral.make("l3");
    	Literal l4 = PosLiteral.make("l4");
    	Clause c1 = makeClause(l1, l2);
    	Clause c2 = makeClause(l3, l4);
    	Formula f = makeFormula(c1, c2);
    	
    	Iterator<Clause> iter = f.iterator();
    	assertTrue(iter.hasNext());
    	assertEquals(iter.next(), c2);
    	assertTrue(iter.hasNext());
    	assertEquals(iter.next(), c1);
    	assertFalse(iter.hasNext());
    }
    
    @Test
    public void testAnd001() {
    	Formula ef1 = new Formula();
    	Formula ef2 = new Formula();
    	Formula ef1AndEf2 = ef1.and(ef2);
    	assertEquals(ef1AndEf2.getSize(), 0);
    	assertTrue(ef1AndEf2.getClauses().isEmpty());
    }
    
    @Test
    public void testAnd002() {
    	Formula ef = new Formula();
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Clause nec = makeClause(a, b);
    	Formula snecf = new Formula(nec);
    	Formula efAndSnecf = ef.and(snecf);
    	assertEquals(efAndSnecf.getSize(), 1);
    	assertEquals(efAndSnecf.getClauses().first(), nec);
    	assertTrue(efAndSnecf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testAnd003() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Clause nec = makeClause(a, b);
    	Formula snecf = new Formula(nec);
    	Formula ef = new Formula();
    	Formula snecfAndEf = snecf.and(ef);
    	assertEquals(snecfAndEf.getSize(), 1);
    	assertEquals(snecfAndEf.getClauses().first(), nec);
    	assertTrue(snecfAndEf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testAnd004() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Literal c = PosLiteral.make("c");
    	Literal d = PosLiteral.make("d");
    	Clause nec1 = makeClause(a, b);
    	Clause nec2 = makeClause(c, d);
    	Formula snecf1 = new Formula(nec1);
    	Formula snecf2 = new Formula(nec2);
    	Formula snecf1AndSnecf2 = snecf1.and(snecf2);
    	assertEquals(snecf1AndSnecf2.getSize(), 2);
    	assertEquals(snecf1AndSnecf2.getClauses().first(), nec2);
    	assertEquals(snecf1AndSnecf2.getClauses().rest().first(), nec1);
    	assertTrue(snecf1AndSnecf2.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testOr001() {
    	Formula ef1 = new Formula();
    	Formula ef2 = new Formula();
    	Formula ef1OrEf2 = ef1.or(ef2);
    	assertEquals(ef1OrEf2.getSize(), 0);
    	assertTrue(ef1OrEf2.getClauses().isEmpty());
    }
    
    @Test
    public void testOr002() {
    	Formula ef = new Formula();
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Clause nec = makeClause(a, b);
    	Formula snecf = new Formula(nec);
    	Formula efOrSnecf = ef.or(snecf);
    	assertEquals(efOrSnecf.getSize(), 1);
    	assertEquals(efOrSnecf.getClauses().first(), nec);
    	assertTrue(efOrSnecf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testOr003() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Clause nec = makeClause(a, b);
    	Formula snecf = new Formula(nec);
    	Formula ef = new Formula();
    	Formula snecfOrEf = snecf.or(ef);
    	assertEquals(snecfOrEf.getSize(), 1);
    	assertEquals(snecfOrEf.getClauses().first(), nec);
    	assertTrue(snecfOrEf.getClauses().rest().isEmpty());
    }
    
    @Test
    public void testOr004() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Literal c = PosLiteral.make("c");
    	Literal d = PosLiteral.make("d");
    	Clause ca = makeClause(a);
    	Clause cb = makeClause(b);
    	Clause cc = makeClause(c);
    	Clause cd = makeClause(d);
    	Formula f1 = makeFormula(ca, cb);
    	Formula f2 = makeFormula(cc, cd);
    	Formula f1OrF2 = f1.or(f2);
    	
    	// (a & b) .or (c & d) == (b | c) & (a | c) & (b | d) & (a | d)
    	assertEquals(f1OrF2.getSize(), 4);
    	Clause firstClause = f1OrF2.getClauses().first();
    	Clause secondClause = f1OrF2.getClauses().rest().first();
    	Clause thirdClause = f1OrF2.getClauses().rest().rest().first();
    	Clause lastClause = f1OrF2.getClauses().rest().rest().rest().first();
    	assertEquals(firstClause, makeClause(b, c));
    	assertEquals(secondClause, makeClause(a, c));
    	assertEquals(thirdClause, makeClause(b, d));
    	assertEquals(lastClause, makeClause(a, d));
    	assertTrue(f1OrF2.getClauses().rest().rest().rest().rest().isEmpty());
    }
    
    @Test
    public void testNot() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Literal c = PosLiteral.make("c");
    	Literal na = a.getNegation();
    	Literal nb = b.getNegation();
    	Literal nc = c.getNegation();
    	Clause aOrB = makeClause(a, b);
    	Clause cc = makeClause(c);
    	Formula f = makeFormula(aOrB, cc);	// f = ((a | b) & c)
    	
        // !((a | b) & c) 
        // => (!b & !a) | !c            (moving negation down to the literals)
        // => (!b | !c) & (!a | !c)     (conjunctive normal form)
    	Formula nf = f.not();
    	Clause ec1 = makeClause(na, nc);
    	Clause ec2 = makeClause(nb, nc);
    	assertEquals(nf.getSize(), 2);
    	assertEquals(nf.getClauses().first(), ec2);
    	assertEquals(nf.getClauses().rest().first(), ec1);
    	assertTrue(nf.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testGetSize001() {
    	Formula ef = new Formula();
    	assertEquals(ef.getSize(), 0);
    	assertTrue(ef.getClauses().isEmpty());
    }
    
    public void testGetSize002() {
    	Literal l = PosLiteral.make("e");
    	Formula f = new Formula(l);
    	assertEquals(f.getSize(), 1);
    	assertTrue(f.getClauses().first().isUnit());
    	assertEquals(f.getClauses().first().chooseLiteral(), l);
    	assertTrue(f.getClauses().rest().isEmpty());
    }
    
    public void testGetSize003() {
    	Literal a = PosLiteral.make("a");
    	Literal b = PosLiteral.make("b");
    	Literal c = PosLiteral.make("c");
    	Literal d = PosLiteral.make("d");
    	Clause nec1 = makeClause(a, b);
    	Clause nec2 = makeClause(c, d);
    	Formula snecf1 = new Formula(nec1);
    	Formula snecf2 = new Formula(nec2);
    	Formula snecf1AndSnecf2 = snecf1.and(snecf2);
    	assertEquals(snecf1AndSnecf2.getSize(), 2);
    	assertEquals(snecf1AndSnecf2.getClauses().first(), nec1);
    	assertEquals(snecf1AndSnecf2.getClauses().rest().first(), nec2);
    	assertTrue(snecf1AndSnecf2.getClauses().rest().rest().isEmpty());
    }
    
    @Test
    public void testToString001() {
    	Formula ef = new Formula();		// an empty formula
    	String es = "Problem[]";
    	assertEquals(ef.toString(), es);
    }
    
    @Test
    public void testToString002() {
    	Literal l = PosLiteral.make("");	// a literal
    	Formula f = new Formula(l);		// a non-empty formula	
    	String es = "Problem[" + "\n" +
    			f.getClauses().first().toString() +"]";
    	assertEquals(f.toString(), es);
    }
    
    @Test
    public void testToString003() {
    	Literal l = PosLiteral.make("l");
    	Clause c = makeClause(l);
    	Formula f = new Formula(c);
    	String es = "Problem[" + "\n" + c.toString() + "]";
    	assertEquals(f.toString(), es);
    }
    
    @Test
    public void testToString004() {
    	Literal l1 = PosLiteral.make("l1");
    	Literal l2 = PosLiteral.make("l2");
    	Clause c = makeClause(l1, l2);
    	Formula f = new Formula(c);
    	String es = "Problem[" + "\n" + c.toString() + "]";
    	assertEquals(f.toString(), es);
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