
self.addEventListener('install', function(event) {
  event.waitUntil(
    caches.open('my-cache').then(function(cache) {
      return cache.addAll([
        '/'
      ]);
    })
  );
});

self.addEventListener('fetch', function(event) {
  event.respondWith(
    caches.match(event.request).then(function(response) {
      return response || fetch(event.request);
    })
  );
});

self.addEventListener('push', function(event) {
  const options = {
    body: event.data.text(),
    icon: 'icon.png',
    badge: 'badge.png',
  };

  event.waitUntil(
    self.registration.showNotification('Título de la notificación', options)
  );
});
