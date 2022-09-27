package com.dave.salinfo.repository;

import com.dave.salinfo.bean.SalaryInfoBean;
import com.dave.salinfo.entity.SalaryInfo;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository
public class SalaryInfoRepositoryImpl {
  @PersistenceContext private EntityManager entityManager;

  public List<SalaryInfoBean> getSalaryInfoBySalaryBetween(
      float min, float max, int offset, Integer limit, String sort) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<SalaryInfoBean> query = cb.createQuery(SalaryInfoBean.class);
    Root<SalaryInfo> root = query.from(SalaryInfo.class);
    query
        .select(cb.construct(SalaryInfoBean.class, root.get("name"), root.get("salary")))
        .where(cb.between(root.get("salary"), min, max));
    if (!(Objects.isNull(sort) || sort.isEmpty())) {
      query.orderBy(cb.asc(root.get(sort.toLowerCase(Locale.ROOT))));
    }

    TypedQuery<SalaryInfoBean> queries = entityManager.createQuery(query);
    queries.setFirstResult(offset);
    if (!Objects.isNull(limit)) {
      queries.setMaxResults(limit);
    }
    return queries.getResultList();
  }
}
