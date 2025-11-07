package ru.rsatu.moderntech.zaoch.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.Dolzhnost;
import ru.rsatu.moderntech.zaoch.pojo.entity.Employee;

import java.util.List;

@ApplicationScoped      //должен существовать в приложении - защита от распознавания как неспользуемого (везде инжекциями)
public class EmployeeRep {
    @Inject
    EntityManager entityManager;    //мeнеждер работы с БД


    /**
     * Загрузка списка сотрудников
     * @return список сотрудников
     */
    public List<Employee> load(){


    //возвращает результат выполнения запроса выборки с сортировокй по возрастанию (ASC) - результат в формате класса, а не просто список
        return entityManager.createQuery("from Employee tbl order by tbl.name ASC",Employee.class).getResultList();

    }


    /**
     * Сохранение или обновление сотрдуника
     * @param db_model модель сотрдуника в БД
     */
    @Transactional  //метод оборачивается в транзакцию
    public void save(Employee db_model){
        if (db_model.getId()!=0){
            entityManager.merge(db_model);  //если ид не равен нулю, то необходимо обновить поле
        }
        else {
            db_model.setId(null);
            entityManager.persist(db_model);    //иначе создать
        }
        entityManager.flush();  //освободить ресурсы
    }

}
