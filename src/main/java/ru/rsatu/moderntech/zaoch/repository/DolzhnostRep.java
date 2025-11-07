package ru.rsatu.moderntech.zaoch.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ru.rsatu.moderntech.zaoch.pojo.entity.Dolzhnost;

import java.util.List;

@ApplicationScoped      //должен существовать в приложении - защита от распознавания как неспользуемого (везде инжекциями)
public class DolzhnostRep {
    @Inject
    EntityManager entityManager;    //мeнеждер работы с БД


    /**
     * Загрузка списка должностей
     * @return список должностей
     */
    public List<Dolzhnost> load(){


    //возвращает результат выполнения запроса выборки с сортировокй по возрастанию (ASC) - результат в формате класса, а не просто список
        return entityManager.createQuery("from Dolzhnost tbl order by tbl.name ASC",Dolzhnost.class).getResultList();

    }


    @Transactional  //метод оборачивается в транзакцию
    public void save(Dolzhnost db_model){
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
