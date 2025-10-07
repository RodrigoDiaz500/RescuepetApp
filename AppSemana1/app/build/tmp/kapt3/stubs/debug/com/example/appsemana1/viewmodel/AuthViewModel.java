package com.example.appsemana1.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u0017J\u0006\u0010!\u001a\u00020\u0017R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR+\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@BX\u0086\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0015\u0010\r\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/example/appsemana1/viewmodel/AuthViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/appsemana1/repository/UserRepository;", "(Lcom/example/appsemana1/repository/UserRepository;)V", "<set-?>", "Lcom/example/appsemana1/viewmodel/AuthState;", "loginState", "getLoginState", "()Lcom/example/appsemana1/viewmodel/AuthState;", "setLoginState", "(Lcom/example/appsemana1/viewmodel/AuthState;)V", "loginState$delegate", "Landroidx/compose/runtime/MutableState;", "recoveryState", "getRecoveryState", "setRecoveryState", "recoveryState$delegate", "registrationState", "getRegistrationState", "setRegistrationState", "registrationState$delegate", "loginUser", "", "email", "", "password", "recoverPassword", "registerUser", "user", "Lcom/example/appsemana1/database/UserEntity;", "resetLoginState", "resetRecoveryState", "resetRegistrationState", "app_debug"})
public final class AuthViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.appsemana1.repository.UserRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState registrationState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState loginState$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableState recoveryState$delegate = null;
    
    public AuthViewModel(@org.jetbrains.annotations.NotNull()
    com.example.appsemana1.repository.UserRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.appsemana1.viewmodel.AuthState getRegistrationState() {
        return null;
    }
    
    private final void setRegistrationState(com.example.appsemana1.viewmodel.AuthState p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.appsemana1.viewmodel.AuthState getLoginState() {
        return null;
    }
    
    private final void setLoginState(com.example.appsemana1.viewmodel.AuthState p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.example.appsemana1.viewmodel.AuthState getRecoveryState() {
        return null;
    }
    
    private final void setRecoveryState(com.example.appsemana1.viewmodel.AuthState p0) {
    }
    
    public final void registerUser(@org.jetbrains.annotations.NotNull()
    com.example.appsemana1.database.UserEntity user) {
    }
    
    public final void loginUser(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
    
    public final void recoverPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void resetRegistrationState() {
    }
    
    public final void resetLoginState() {
    }
    
    public final void resetRecoveryState() {
    }
}