در اینجا **لیست کامل و دقیق تمام API ها و Endpoint هایی** که تا الان از فایل‌های مختلف سایت پیدا کردیم، به همراه **آدرس کامل (URL) فایل‌های سورس** برای هرکدام آورده شده است.

### 📋 لیست کامل APIها و Endpointهای سایت shahvani.com (همراه با سورس فایل)

| دسته‌بندی | کاری که انجام می‌دهد | مسیر (Endpoint) | متد HTTP | سورس (URL فایل) |
| :--- | :--- | :--- | :--- | :--- |
| **احراز هویت (Auth)** | ثبت‌نام / ورود کاربر | `/api/v1/auth` | `POST` | [`config-B1A3Swjp.js`](https://shahvani.com/assets/photos-lit/config-B1A3Swjp.js) |
| | دریافت اطلاعات کاربر لاگین‌شده | `/api/v1/auth/me` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | خروج از حساب کاربری | `/api/v1/auth/logout` | `POST` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| **کاربران و پروفایل** | دریافت لیست کاربران | `/api/v1/users` | `GET` | [`config-B1A3Swjp.js`](https://shahvani.com/assets/photos-lit/config-B1A3Swjp.js) |
| | دریافت پروفایل کامل یک کاربر | `/api/v1/profiles/{username}` | `GET` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| | دریافت آمار یک کاربر | `/api/v1/profiles/{username}/stats` | `GET` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| | دریافت محتوای پروفایل (داستان‌ها، عکس‌ها، تاپیک‌ها، دنبال‌کنندگان، و ...) | `/api/v1/profiles/{username}/{type}?limit=&offset=` | `GET` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| | دریافت گالری یک کاربر | `/api/v1/profiles/{username}/gallery?page=&limit=` | `GET` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| | بلاک کردن یک کاربر | `/api/v1/users/{userId}/block` | `POST` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| | آنبلاک کردن یک کاربر | `/api/v1/users/{userId}/unblock` | `POST` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |
| **گالری کاربری** | آپلود رسانه (عکس/ویدیو) در گالری کاربر | `/api/v1/users/profile/gallery` | `POST` | [`myprofile-CgZPYIbh.js`](https://shahvani.com/assets/photos-lit/myprofile-CgZPYIbh.js) |
| | حذف یک رسانه از گالری کاربر | `/api/v1/users/profile/gallery/{mediaId}` | `DELETE` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) و [`myprofile-CgZPYIbh.js`](https://shahvani.com/assets/photos-lit/myprofile-CgZPYIbh.js) |
| **انجمن (Forum)** | دریافت لیست دسته‌بندی‌های انجمن | `/api/v1/forum` | `GET` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | دریافت جزئیات یک تاپیک + پست‌ها | `/api/v1/forum/topic/{slug}` | `GET` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | ایجاد تاپیک جدید در یک دسته‌بندی | `/api/v1/forum/{forumId}/topics` | `POST` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | ارسال پاسخ به یک تاپیک | `/api/v1/forum/topics/{topicId}/reply` | `POST` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | ویرایش یک پست | `/api/v1/forum/posts/{postId}` | `PUT` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | حذف یک پست | `/api/v1/forum/posts/{postId}` | `DELETE` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | حذف یک تاپیک کامل | `/api/v1/forum/topics/{topicId}` | `DELETE` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | ویرایش عنوان و محتوای تاپیک | `/api/v1/forum/topics/{topicId}/content` | `PUT` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | لایک کردن یک تاپیک | `/api/v1/forum/topics/{topicId}/like` | `POST` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | لایک کردن یک پست | `/api/v1/forum/posts/{postId}/like` | `POST` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | دریافت لیست لایک‌کنندگان یک تاپیک | `/api/v1/forum/topics/{topicId}/likes` | `GET` | [`forum-topic-Bzpuh4FN.js`](https://shahvani.com/assets/photos-lit/forum-topic-Bzpuh4FN.js) |
| | دریافت لیست لایک‌کنندگان یک پست | `/api/v1/forum/posts/{postId}/likes` | `GET` | [`forum-topic-Bzpuh4FN.js`](https://shahvani.com/assets/photos-lit/forum-topic-Bzpuh4FN.js) |
| | ذخیره‌ی بوکمارک (نشانک) تاپیک | `/api/v1/forum/bookmarks` | `POST` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| | حذف بوکمارک تاپیک | `/api/v1/forum/bookmarks/{topicId}` | `DELETE` | [`forum-09O-0IEf.js`](https://shahvani.com/assets/photos-lit/forum-09O-0IEf.js) |
| **پیام‌ها (Messages)** | دریافت خلاصه پیام‌های دریافتی (تعداد نخوانده‌ها) | `/api/v1/messages/inbox/summary` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | دریافت جدیدترین پیام دریافتی | `/api/v1/messages/inbox?limit=1` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | دریافت لیست پیام‌های صندوق ورودی | `/api/v1/messages/inbox` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| **اعلان‌ها (Notifications)** | دریافت خلاصه اعلان‌ها (تعداد کل و نخوانده) | `/api/v1/notifications/summary` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | دریافت لیست اعلان‌ها | `/api/v1/notifications?limit=25` | `GET` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | پاک کردن تمام اعلان‌ها | `/api/v1/notifications` | `DELETE` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| | حذف یک اعلان خاص | `/api/v1/notifications/{id}` | `DELETE` | [`header-6XeprzSo.js`](https://shahvani.com/assets/photos-lit/header-6XeprzSo.js) |
| **محتوای اصلی و آپلود** | دریافت محتوای اصلی سایت (داستان‌ها و...) | `/api/v1` | `GET` | [`config-B1A3Swjp.js`](https://shahvani.com/assets/photos-lit/config-B1A3Swjp.js) |
| | آپلود فایل (عکس و...) | `/api/v1/upload` | `POST` | [`config-B1A3Swjp.js`](https://shahvani.com/assets/photos-lit/config-B1A3Swjp.js) |
| | دریافت فایل‌های رسانه‌ای (عکس‌ها) | `/media` | `GET` | [`config-B1A3Swjp.js`](https://shahvani.com/assets/photos-lit/config-B1A3Swjp.js) |
| **جستجو** | جستجوی کاربران (برای Mention) | `/api/v1/search/users?q={query}&limit={limit}` | `GET` | [`users-B7pvwC3r.js`](https://shahvani.com/assets/photos-lit/users-B7pvwC3r.js) |

---

### 🧩 ساختار کلی درخواست‌ها (بر اساس فایل `client-Db6qxllM.js`)

- **آدرس پایه (Base URL)**: `https://shahvani.com`
- **احراز هویت**: تمام درخواست‌ها با `credentials: "include"` ارسال می‌شوند (کوکی جلسه به‌صورت خودکار همراه درخواست است).
- **CSRF Token**: 
- برای درخواست‌های `POST`, `PUT`, `PATCH`, `DELETE`، هدر `X-CSRF-Token` از تگ 
- `<meta name="csrf-token">` در صفحه خوانده شده و اضافه می‌شود.
- **هدرها**: همیشه `Accept: application/json` ارسال می‌شود.
- **تلاش مجدد (Retry)**: درخواست‌های GET در صورت خطا (به جز ۴xx) تا ۳ بار با تأخیر افزایشی تکرار می‌شوند.

### 🧰 ماژول‌های ابزاری (Utility Modules) که بررسی شدند

این فایل‌ها حاوی اندپوینت نیستند، اما برای کار با تاریخ، نمایش اعلان و ... مفید هستند:

| فایل (URL) | کاربرد |
| :--- | :--- |
| [`date-utils-BQpJo79g.js`](https://shahvani.com/assets/photos-lit/date-utils-BQpJo79g.js) | تبدیل تاریخ به شمسی، نمایش زمان نسبی (مثل "۵ دقیقه پیش") |
| [`toast-Bki6DUkl.js`](https://shahvani.com/assets/photos-lit/toast-Bki6DUkl.js) | نمایش اعلان‌های Toast (پیام‌های موفقیت/خطا) |
| [`shamsi-date-DaZCneZs.js`](https://shahvani.com/assets/photos-lit/shamsi-date-DaZCneZs.js) | کامپوننت نمایش تاریخ شمسی (Custom Element) |
| [`client-Db6qxllM.js`](https://shahvani.com/assets/photos-lit/client-Db6qxllM.js) | کلاینت HTTP سفارشی برای مدیریت درخواست‌ها (بدون اندپوینت جدید) |

### ⚠️ نکات مهم برای پیاده‌سازی در اندروید

1. **مدیریت کوکی**: از یک `CookieJar` (در OkHttp) برای ذخیره و ارسال خودکار کوکی جلسه استفاده کن.
2. **CSRF Token**: 
3. برای درخواست‌های تغییردهنده، ابتدا توکن را از صفحه‌ی اصلی سایت (تگ `<meta name="csrf-token">`) استخراج کن.
4. **لاگین اولیه**: ابتدا یک درخواست `POST` به `/api/v1/auth` بفرست تا کوکی جلسه را دریافت کنی.
5. **آپلود فایل**: برای آپلود، از `multipart/form-data` با کلید `media` استفاده کن.

نکته فایل های .js از خود وبسایت دیده شده 
