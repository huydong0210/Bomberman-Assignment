#Game bomberman :
## Gói cơ bản :
- Đã thiết kế cây hướng đối tượng
- Xây dựng được bản đồ theo file mẫu
- Di chuyển bomberman theo sự điều khiển của người chơi
- tự động di chuyển các Enemy
- xử lý bom nổ
- xử lý khi bomber sử dụng item và đi vào ví trí portal
## Gói nâng cao :
- Đã tối ưu thuật toán tìm đường cho enemy
- Cài đặt các enemy:
  - Balloom : di chuyển ngẫu nhiên với vận tốc cố định (gói cơ bản)
  - Oneal : di chuyển ngẫu nhiên với vận tốc lúc nhanh lúc chậm, có thể chạy đuổi theo player (gói cơ bản)
  - Doll : di chuyển nhanh, ngẫu nhiên , khi chết sinh ra 1 balloom
  - Kondoria : di chuyển chậm, ngẫu nhiên, đi xuyên qua được brick
  - Minvo: di chuyển trung bình, ngẫu nhiên, biết bám theo player, khi chết sinh ra 1 kondoria
- Cài đặt các item
  - bombpass : nâng cấp phạm vi bom nổ
  - bomitem : tăng số lượng boom có thể đặt cùng 1 lúc
  - brickpass: đi xuyên qua brick
  - heart : tăng mạng
  - powerupspeed: tăng tốc độ player
- Đã thêm hiệu ứng âm thanh
- Player có số mạng, khi chết hết mạng sẽ hiện lên màn hình game over ( có nhạc)
- Player khi bắt đầu chơi, hoặc khi sống dậy sẽ có trạng thái vô địch 4 giây không bị chết
- Tối ưu khả năng di chuyển của player ( di chuyển player dễ hơn, mềm hơn)
- Khi đi vào công portal ( đã chết hết enemy) sẽ hiện màn hình nextlevel và có nhạc

    
      