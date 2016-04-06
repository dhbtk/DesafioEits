package com.stockontrol.domain.util;

import java.util.ArrayList;

import com.mysema.query.types.expr.BooleanExpression;

/**
 * Classe helper para gerar listas de condições para chamadas SQL
 * 
 * @author diana
 *
 */
public class PredicateList
{
	public interface PredicateWrapper
	{
		BooleanExpression expr();
	}

	private ArrayList<BooleanExpression> predicates = new ArrayList<BooleanExpression>();

	/**
	 * Adiciona uma expressão à lista caso param não seja nulo.
	 * 
	 * @param param
	 *            O parâmetro cuja nulidade será testada
	 * @param predicate
	 *            Uma expressão lambda que retorne a expressão a ser adicionada.
	 *            Um lambda é necessário para evitar que a expressão seja
	 *            avaliada caso o objeto seja nulo.
	 * @return Esta instância da classe para encadeamento de chamadas
	 */
	public PredicateList add(Object param, PredicateWrapper predicate)
	{
		if (param != null)
		{
			predicates.add(predicate.expr());
		}
		return this;
	}

	/**
	 * Adiciona uma expressão à lista.
	 * 
	 * @param predicate
	 *            A expressão a ser adicionada
	 * @return Esta instância da classe para encadeamento de chamadas
	 */
	public PredicateList add(BooleanExpression predicate)
	{
		predicates.add(predicate);
		return this;
	}

	/**
	 * Retorna a expressão que é a interseção de todas as condições da lista, ou
	 * seja, a adição lógica de todas elas.
	 * 
	 * @return A expressão reduzida
	 */
	public BooleanExpression getIntersection()
	{
		BooleanExpression[] a = {};
		return BooleanExpression.allOf(predicates.toArray(a));
	}
}
