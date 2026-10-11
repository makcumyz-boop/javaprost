import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ScholarshipManager <T extends ScholarshipApplication>{
    private List<T> applications = new ArrayList<>();
    Predicate<List<T>> cond = apps -> {
        if (apps.size() < 2) return false;
        double min = apps.stream().mapToDouble(T::calculateScore).min().orElse(0);
        double max = apps.stream().mapToDouble(T::calculateScore).max().orElse(0);
        return min >= 60 && max - min <=20;
    };
     public boolean add(T application){
         if (application == (null) || applications.stream().anyMatch( d -> d.getId() == application.getId())){
             return false;
         }
         else {
             applications.add(application);
             applications.stream().forEach(d -> d.setAwarded(false));
             return true;
         }
     }
    List<T> allocate(int limit){
         if (limit < 0){
             throw new IllegalArgumentException();
         }
         List<T> winners = new ArrayList<>();
         List<Map.Entry<Integer, List<T>>> wins = students(applications).entrySet().stream().filter(d -> cond.test(d.getValue())).sorted(Comparator.comparingDouble((Map.Entry<Integer,List<T>> d) -> rating(d.getValue())).reversed().thenComparingInt((Map.Entry<Integer,List<T>> d) -> d.getKey())).limit(limit).toList();
         for (Map.Entry<Integer, List<T>> w : wins ){
             T best = w.getValue().stream().max(Comparator.comparingDouble(T::calculateScore)).get();
             best.setAwarded(true);
             winners.add(best);
         }
         return winners;




    }
    OptionalDouble getStudentRating(int studentId){
         if (studentId <=0 ){
             throw new IllegalArgumentException();
         }
         if (applications.stream().noneMatch(d -> d.getStudentId() == studentId )){
             return OptionalDouble.empty();

        }

        return students(applications).entrySet().stream().filter(d -> d.getKey() == studentId).findFirst().map(Map.Entry::getValue).filter(cond).stream().flatMap(List::stream).mapToDouble(T::calculateScore).min();






    }
    Map<Integer, List<T>> students(List<T> apps){
          return apps.stream().collect(Collectors.groupingBy(T::getStudentId));
    }
    Double rating (List<T> value){
         return value.stream().mapToDouble(T::calculateScore).min().orElse(0);

    }

}
