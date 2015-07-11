package com.mt.algorithm.ssa;

import org.springframework.beans.factory.annotation.Autowired;

import com.mt.models.SSAParams;
import com.mt.models.dao.SSAParamsDao;

/**
 * The <code>SSACalculator</code> enum is the Social Stairway Algorithm Calculator.
 * 
 * An enum that isn't enclosed in a class can be declared only the public or default modifier, just like a non-inner class (outer class).
 * Enums or Enumerated List can be declared as their own class, because an enum really is a special kind of class, you can do more than
 * just list the enumarated constant values.  You can add constructors, instance variables, methods, and 'constant specific class body',
 * like an anonymous inner class.
 * 
 * @author gibranecastillo
 *
 */
public enum SSACalculator {
	/*
	 * It's not required that enum constants be in all caps.
	 * Kf is of type SSACalculator.  Ks is of type SSACalculator.
	 * They're represented as static and final, which in the Java world, is thought of as a constant.
	 * Note that each enum value knows its index or position; in other words, the order in which enum values are declared matters.
	 * You can think of the SSACalculator enums as existing in an array of type SSACalculator.  You can iterate through the values of an
	 * enum by invoking the 'values()' method on any enum type.
	 * 
	 * The following is a conceptual example of how you can think about enums:
	 * public class SSACalculator {
	 *     private String enumName;
	 *     private int enumIndex;
	 *     private double enumValue;
	 *     public static final SSACalculator Kf = new SSACalculator("Kf", 0, 0.2); // enumName, enumIndex, enumValue
	 *     public static final SSACalculator Ks = new SSACalculator("Ks", 1, 0.2); // enumName, enumIndex, enumValue
	 *     
	 *     // Constructs a SSACalculator instance
	 *     public SSACalculator(String enumName, int enumIndex, double enumValue) {
	 *         this.enumName = enumName;
	 *         this.enumIndex = enumIndex;
	 *         this.enumValue = enumValue;
	 *     }
	 *     
	 *     ........
	 * }
	 */
	
	// Friend Feed to School Feed Threshold Ratio.
	Kf(0.20), // calls constructor with value 0.2
	
	// School Feed to National Feed Threshold Ratio.
	Ks(0.20); // calls constructor with value 0.2
	
	@Autowired
	private SSAParamsDao _ssaParamsDao;
	
	// Preliminary Post Coefficient.
	private final double Cf;
	
	// Ideal Number of posts in School feed.
	private final long NsIdeal;
	
	// Ideal Number of posts in National feed.
	private final long NnIdeal;
	
	// T1, T2, T3, and T4 are Tuning Coefficients.
	private final double T1, T2, T3, T4;
	
	// value to be added to enum.
	private final double value;
	
	/*
	 * An instance initialization block runs every time a new instance is
	 * created.  It runs right after the call to super() in a constructor,
	 * in other words, after all the super-constructors have run.
	 */
	{
		SSAParams ssaParams = _ssaParamsDao.getSSAParams();
		Cf = ssaParams.getCf();
		NsIdeal = ssaParams.getNsIdeal();
		NnIdeal = ssaParams.getNnIdeal();
		T1 = ssaParams.getT1();
		T2 = ssaParams.getT2();
		T3 = ssaParams.getT3();
		T4 = ssaParams.getT4();
	}
	
	/**
	 * Constructs an SSACalculator enum instance.
	 * <> You can NEVER invoke an enum constructor directly [explicitly].  The enum constructor is invoked automatically [implicitly],
	 *    with the arguments you define after the constant value.  For example, Kf(0.2) invokes the SSACalculator constructor that takes
	 *    a double, passing the double literal 0.2 to the constructor.  Behind the scenes, of course, you can imagine that the String 
	 *    'Kf' and int index 0 is also passed to the constructor, but we don't have to know or worry about those details.
	 * 
	 * <> You can define more than one argument to the constructor, and you can overload the enum constructor just as you overload
	 *    a normal class constructor.
	 * 
	 * @param value
	 */
	SSACalculator(double value) {
		this.value = value;
	}
	
	/**
	 * Gets the value added to enum.
	 * 
	 * @return the value added to enum.
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post can be display or publish in the School Feed as well.
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRf(long V, long L, long Ns) {
		double Rf = 0.0;
		
		Rf = (L/V) * Cf * this.calculateCns(Ns);
		
		return Rf; 
	}
	
	/**
	 * Calculates the School Feed Capacity Coefficient.
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated school feed capacity coefficient.
	 */
	public double calculateCns(long Ns) {
		double Cns = 0.0;
		
		Cns = (NsIdeal/Ns) * T1;
		
		return Cns;
	}
	
	/**
	 * Calculates the Post Ratio to determine if a Friend Feed post that is already showing in
	 * the School Feed can be display or publish in the National Feed as well.
	 * 
	 * @param V a long value that represents the number of 'views' on a post.
	 * @param L a long value that represents the number of 'likes' on a post.
	 * @param Cr a long value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a long value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * @param Cpn a long value that represents the number of 'post' from that school.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school. 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated post ratio.
	 */
	public double calculateRs(long V, long L, long Cr, double CrIdealAvg, long Cl, double ClIdealAvg, long Cpn, double CpnAvg, long Ns) {
		double Rs = 0.0;
		double Cs = this.calculateCs(Cr, CrIdealAvg, Cl, ClIdealAvg);
		double Cp = this.calculateCp(Cpn, CpnAvg);
		double Cnn = this.calculateCnn(Ns);
		
		Rs = (L/V) * Cs * Cp * Cnn;
		
		return Rs;
	}
	
	/**
	 * Calculates the School Coefficient.
	 * 
	 * @param Cr a long value that represents the number of 'people' registered from that school.
	 * @param CrIdealAvg a double value that represents the average number of 'people' registered per school.
	 * @param Cl a long value that represents the number of 'likes' per post from that school.
	 * @param ClIdealAvg a double value that represents the average number of 'likes' per post from all school.
	 * 
	 * @return a double value that represents the calculated school coefficient.
	 */
	public double calculateCs(long Cr, double CrIdealAvg, long Cl, double ClIdealAvg) {
		double Cs = 0.0;
		
		Cs = ((Cr/CrIdealAvg) * (Cl/ClIdealAvg)) * T2;
		
		return Cs;
	}
	
	/**
	 * Calculates the Post Flux Coefficient.
	 * 
	 * @param Cpn a long value that represents the number of 'post' from that school.
	 * @param CpnAvg a double value that represents the average number of 'post' from that school.
	 * 
	 * @return a double value that represents the calculated post flux coefficient.
	 */
	public double calculateCp(long Cpn, double CpnAvg) {
		double Cp = 0.0;
		
		Cp = (CpnAvg/Cpn) * T3;
		
		return Cp;
	}
	
	/**
	 * Calculates the National Feed Capacity Coefficient.
	 * 
	 * @param Ns a long value that represents the number of 'posts' in School Feed.
	 * 
	 * @return a double value that represents the calculated national feed capacity coefficient.
	 */
	public double calculateCnn(long Ns) {
		double Cnn = 0.0;
		
		Cnn = (NnIdeal/Ns) * T4;
		
		return Cnn;
	}
}