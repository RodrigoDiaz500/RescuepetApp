package com.example.appsemana1.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/appsemana1/database/UserDao;", "", "getUserByCredentials", "Lcom/example/appsemana1/database/UserEntity;", "email", "", "password", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserByEmail", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertUser", "", "user", "(Lcom/example/appsemana1/database/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface UserDao {
    
    /**
     * Inserta un nuevo usuario
     */
    @androidx.room.Insert()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertUser(@org.jetbrains.annotations.NotNull()
    com.example.appsemana1.database.UserEntity user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Busca un usuario por credenciales para el Login
     */
    @androidx.room.Query(value = "SELECT * FROM user_table WHERE correo = :email AND contrasena = :password LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByCredentials(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.appsemana1.database.UserEntity> $completion);
    
    /**
     * Busca un usuario por correo para verificar si ya existe (Registro/Recuperación)
     */
    @androidx.room.Query(value = "SELECT * FROM user_table WHERE correo = :email LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserByEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.appsemana1.database.UserEntity> $completion);
}