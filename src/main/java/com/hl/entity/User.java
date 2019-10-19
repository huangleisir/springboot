package com.hl.entity;
import lombok.ToString;

import java.util.Objects;

// Lombok更类似于一种IDE插件，项目也需要依赖相应的jar包。Lombok依赖jar包是因为编译时要用它的注解，
// @Data太过残暴（因为@Data集合了@ToString、@EqualsAndHashCode、@Getter/@Setter、@RequiredArgsConstructor的所有特性）
//@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
//无参构造器、部分参数构造器、全参构造器。Lombok没法实现多种参数构造器的重载。
//@Cleanup   该注解能帮助我们自动调用close()方法，很大的简化了代码。
// @NonNull
//该注解用在属性或构造器上，Lombok会生成一个非空的声明，可用于校验参数，能帮助避免空指针。
/*@Getter
@Setter*/
@ToString
//@Data
public class User{
    private String name;
    private int age;
    private String idCardNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(idCardNum, user.idCardNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, idCardNum);
    }
}