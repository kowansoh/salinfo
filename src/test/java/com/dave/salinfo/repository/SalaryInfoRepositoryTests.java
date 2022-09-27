package com.dave.salinfo.repository;

import com.dave.salinfo.bean.SalaryInfoBean;
import com.dave.salinfo.entity.SalaryInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalaryInfoRepositoryTests {

  @InjectMocks SalaryInfoRepositoryImpl classUnderTest;
  @Mock EntityManager entityManager;

  @Test
  @DisplayName("Get Salary Info without sorting and limit")
  void testGetSalaryInfoBySalaryBetween() {
    CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
    CriteriaQuery query = Mockito.mock(CriteriaQuery.class);
    TypedQuery queries = Mockito.mock(TypedQuery.class);
    Root root = Mockito.mock(Root.class);
    CompoundSelection selection = Mockito.mock(CompoundSelection.class);

    List<SalaryInfoBean> salaryList =
        List.of(new SalaryInfoBean("name", 1), new SalaryInfoBean("name 2", 2));
    Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
    Mockito.when(cb.createQuery(Mockito.any())).thenReturn(query);
    Mockito.when(query.from(SalaryInfo.class)).thenReturn(root);
    Mockito.when(cb.construct(Mockito.any(), Mockito.any(Path.class), Mockito.any(Path.class)))
        .thenReturn(selection);
    Mockito.when(query.select(Mockito.any())).thenReturn(query);
    Mockito.when(entityManager.createQuery(Mockito.any(CriteriaQuery.class))).thenReturn(queries);
    Mockito.when(queries.getResultList()).thenReturn(salaryList);
    List<SalaryInfoBean> response =
        classUnderTest.getSalaryInfoBySalaryBetween(0, 1, 0, null, null);
    Mockito.verify(query, Mockito.times(0)).orderBy(Mockito.any(Order.class));
    Mockito.verify(queries, Mockito.times(0)).setMaxResults(Mockito.anyInt());

    Assertions.assertEquals(response, salaryList);
  }

  @Test
  @DisplayName("Get Salary Info with sorting and limit")
  void testGetSalaryInfoBySalaryBetweenWithSortingAndLimit() {
    CriteriaBuilder cb = Mockito.mock(CriteriaBuilder.class);
    CriteriaQuery query = Mockito.mock(CriteriaQuery.class);
    TypedQuery queries = Mockito.mock(TypedQuery.class);
    Root root = Mockito.mock(Root.class);
    Order order = Mockito.mock(Order.class);

    List<SalaryInfoBean> salaryList =
        List.of(new SalaryInfoBean("name", 1), new SalaryInfoBean("name 2", 2));
    Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
    Mockito.when(cb.createQuery(Mockito.any())).thenReturn(query);
    Mockito.when(query.from(SalaryInfo.class)).thenReturn(root);
    Mockito.when(query.select(Mockito.any())).thenReturn(query);
    Mockito.when(query.where(Mockito.any(Predicate.class))).thenReturn(query);
    Mockito.when(cb.asc(Mockito.any())).thenReturn(order);
    Mockito.when(queries.getResultList()).thenReturn(salaryList);
    Mockito.when(entityManager.createQuery(Mockito.any(CriteriaQuery.class))).thenReturn(queries);
    Mockito.when(queries.getResultList()).thenReturn(salaryList);
    List<SalaryInfoBean> response = classUnderTest.getSalaryInfoBySalaryBetween(0, 1, 0, 4, "NAME");
    Mockito.verify(query, Mockito.times(1)).orderBy(Mockito.any(Order.class));
    Mockito.verify(queries, Mockito.times(1)).setMaxResults(Mockito.anyInt());

    Assertions.assertEquals(response, salaryList);
  }
}
