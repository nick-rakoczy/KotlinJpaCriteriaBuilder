package ktmetamodel

import javax.persistence.criteria.AbstractQuery
import javax.persistence.criteria.CollectionJoin
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaDelete
import javax.persistence.criteria.CriteriaUpdate
import javax.persistence.criteria.Fetch
import javax.persistence.criteria.FetchParent
import javax.persistence.criteria.From
import javax.persistence.criteria.Join
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.ListJoin
import javax.persistence.criteria.MapJoin
import javax.persistence.criteria.Path
import javax.persistence.criteria.SetJoin
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1

fun <T : Any> CriteriaBuilder.createQueryKt(type: KClass<T>) = this.createQuery(type.java)
fun <T : Any> CriteriaBuilder.createCriteriaUpdateKt(type: KClass<T>) = this.createCriteriaUpdate(type.java)
fun <T : Any> CriteriaBuilder.createCriteriaDeleteKt(type: KClass<T>) = this.createCriteriaDelete(type.java)

fun <T : Any, X : Any> AbstractQuery<T>.from(type: KClass<X>) = this.from(type.java)
fun <T : Any> CriteriaUpdate<T>.from(type: KClass<T>) = this.from(type.java)
fun <T : Any> CriteriaDelete<T>.from(type: KClass<T>) = this.from(type.java)

operator fun <X : Any, Y : Any> Path<X>.get(attr: KProperty1<X, Y>): Path<Y>
		= this[attr.name]

fun <Z, X, Y> From<Z, X>.join(attr: KProperty1<X, Y>, joinType: JoinType = JoinType.INNER): Join<X, Y>
		= this.join(attr.name, joinType)

fun <Z, X, Y, C : Collection<Y>> From<Z, X>.joinCollection(attr: KProperty1<X, C?>, joinType: JoinType = JoinType.INNER): CollectionJoin<X, Y>
		= this.joinCollection(attr.name, joinType)

fun <Z, X, Y, C : Set<Y>> From<Z, X>.joinSet(attr: KProperty1<X, C?>, joinType: JoinType = JoinType.INNER): SetJoin<X, Y>
		= this.joinSet(attr.name, joinType)

fun <Z, X, Y, C : List<Y>> From<Z, X>.joinList(attr: KProperty1<X, C?>, joinType: JoinType = JoinType.INNER): ListJoin<X, Y>
		= this.joinList(attr.name, joinType)

fun <Z, X, K, V, M : Map<K, V>> From<Z, X>.joinMap(attr: KProperty1<X, M?>, joinType: JoinType = JoinType.INNER): MapJoin<X, K, V>
		= this.joinMap(attr.name, joinType)

fun <Z, X, Y> FetchParent<Z, X>.fetch(attr: KProperty1<X, Y>, joinType: JoinType = JoinType.INNER): Fetch<X, Y>
		= this.fetch(attr.name, joinType)
