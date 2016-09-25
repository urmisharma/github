package edu.uno.ai.planning.bfs;


import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import edu.uno.ai.planning.Plan;
import edu.uno.ai.planning.Step;
import edu.uno.ai.planning.logic.Conjunction;
import edu.uno.ai.planning.logic.Expression;
import edu.uno.ai.planning.logic.Literal;
import edu.uno.ai.planning.ss.StateSpaceNode;
import edu.uno.ai.planning.ss.StateSpaceProblem;
import edu.uno.ai.planning.ss.StateSpaceSearch;
import edu.uno.ai.planning.util.ImmutableArray;
/**
 * Searches for a solution using A* Search.
 * 
 * @author Urmila Sharma
 */

public class BreadthFirstSearch extends StateSpaceSearch
{
	Comparator<QNode> comparator = new QNode();
	protected final PriorityQueue<QNode> pQueue = new PriorityQueue<QNode>(10, comparator);

	java.util.HashSet<Literal> literals = new java.util.HashSet<>();
	ImmutableArray<Step> steps = null;
	
	public BreadthFirstSearch(StateSpaceProblem problem)
	{
		super(problem);
		QNode rootNode = new QNode();
		rootNode.ssn = root;
		rootNode.value = 0.0;
		steps = problem.steps;
		pQueue.offer(rootNode);
	}

	@Override
	public Plan findNextSolution() 
	{
		
		HashSet<Literal> all_literals = getLiterals(problem);
				
		while(!pQueue.isEmpty()) 
		{
			QNode qn = pQueue.poll();
			StateSpaceNode node = qn.ssn;
			StateSpaceNode child = null;
			
			if(problem.goal.isTrue(node.state))
				return node.plan;
			for(Step step : steps)
			{
				if (step.precondition.isTrue(node.state))
				{
					child = node.expand(step);
					Heuristic a = new Heuristic();
					int dist_from_root_to_current = child.plan.size();
					QNode subNode = new QNode();
					subNode.ssn = child;
					subNode.value = dist_from_root_to_current+a.evaluate(subNode.ssn, problem.goal, all_literals, steps);
					pQueue.offer(subNode);
				}
			}
		}
				
		return null;
	}
	
	HashMap<Literal, Double> literals_map = new HashMap<Literal, Double>();
	
	public HashSet<Literal> getLiterals(StateSpaceProblem problem)
	{
		
		steps = problem.steps;
		Expression goal_exp = problem.goal;
		Expression root_exp = root.state.toExpression();
		
		if(goal_exp instanceof Literal)
		{
			literals.add((Literal) goal_exp);
		}
		else 
		{
			Conjunction conj = (Conjunction) goal_exp;
		    for(Expression conjunct : conj.arguments)
		    	literals.add((Literal) conjunct);
		}
		if(root_exp instanceof Literal)
		{
			literals.add((Literal) root_exp);
		}
		else
		{
			Conjunction conj = (Conjunction) root_exp;
		    for(Expression conjunct : conj.arguments)
		        literals.add((Literal) conjunct);
		}
		
		for(int i = 0 ; i < steps.length; i++)
		{
			Expression precond_exp = steps.get(i).precondition;
			Expression effect_exp = steps.get(i).effect;
			
			if(precond_exp instanceof Literal)
			{
				literals.add((Literal) precond_exp);
							
			}
			else
			{
				Conjunction conj = (Conjunction) precond_exp;
			    for(Expression conjunct : conj.arguments)
			        literals.add((Literal) conjunct);
			}
			
			if(effect_exp instanceof Literal)
			{
				literals.add((Literal) effect_exp);
			}
			else
			{
				Conjunction conj = (Conjunction) effect_exp;
			    for(Expression conjunct : conj.arguments)
			        literals.add((Literal) conjunct);
			}
						
		}
		return literals;
		
	}
	
}


