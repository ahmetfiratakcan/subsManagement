# Subscription Management

# Transaction Yönetimi

Bu proje, **Saga Pattern** kullanarak transaction yönetimini ele alır. Kareografi modelini kullanarak servisler arası event-driven bir işlem akışı oluşturulmuştur.

## İş Akışı

### 1. Abonelik Oluşumu

- **Subscription servisi**, bir kullanıcıdan gelen abonelik isteğini alır ve abone oluşturma işlemi başlatılır.
- Abonelik başarıyla oluşturulduktan sonra, ödeme servisine bir event gönderilir.

### 2. Ödeme İşlemi

- **Payment servisi**, gelen event ile ödeme sürecini başlatır.
- Ödeme işlemi sonucu:
    - Ödeme başarılıysa, `payment_success` eventi gönderilir.
    - Ödeme başarısız olursa, `payment_failed` eventi gönderilir.

### 3. Subscription Servisi

- **Subscription servisi**, ödeme işleminin sonucunu dinler ve üyeliği günceller:
    - Ödeme başarılıysa, üyelik durumu **ACTIVE** olarak güncellenir.
    - Ödeme başarısız olursa, üyelik durumu **INACTIVE** olarak güncellenir.