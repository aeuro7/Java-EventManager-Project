# Project CS211 (nebb_group)

## รายชื่อสมาชิก
* 6510450399 นายทิเบต เจริญศรีไพบูลย์ aeuro7
   * 17 การเข้าร่วม Event
     * 17.a  มีหน้าแสดงรายการอีเวนต์ที่อยู่ระหว่างการจัดงานทั้งหมดให้ผู้ใช้ทั่วไปดู โดยแสดงภาพอีเวนต์ ชื่ออีเวนต์ และจำนวนที่ว่างเหลือสำหรับเข้าร่วมอีเวนต์
     * 17.b มีหน้าแสดงประวัติรายการอีเวนต์ที่ตนเองเข้าร่วม โดยแยกอีเวนต์ที่อยู่ระหว่างการจัดงาน และอีเวนต์ที่สิ้นสุดแล้ว
     * 17.c มีส่วนให้ค้นหาอีเวนต์ด้วยบางส่วนของชื่ออีเวนต์
   * 18.b มีหน้าแสดงรายการอีเวนต์ที่ตนเองเป็นผู้จัดอีเวนต์ ซึ่งสามารถแสดงหน้าจัดการอีเวนต์ได้
* 6510450534 นางสาวนุตประวีณ์ กาวี nammeyyy
    * 16 การสร้างบัญชีของผู้ใช้ทั่วไป
      * 16.a ระบบลงทะเบียน (registration) สำหรับผู้ใช้ทั่วไป
        ข้อมูลการลงทะเบียนได้แก่ 
        * ชื่อสำหรับเข้าสู่ระบบ (username) 
        * รหัสผ่าน และการยืนยันรหัสผ่าน
        * ชื่อของผู้ใช้ระบบ
      * 16.c  ผู้ใช้ระบบสามารถเปลี่ยนรูปภาพเพื่อใช้เป็นภาพของผู้ใช้ระบบ หากผู้ใช้ไม่เปลี่ยนหรือยังไม่กำหนดภาพ ระบบจะให้ใช้ภาพ default แทนไปก่อน
   * 18 การสร้าง Event
     * 18.a มีส่วนให้ผู้ใช้ทั่วไปสร้างอีเวนต์ เพื่อเป็น “ผู้จัดอีเวนต์” โดยต้องระบุข้อมูลต่อไปนี้
       - ชื่ออีเวนต์ <br>
       - ภาพอีเวนต์ <br>
       - รายละเอียดของอีเวนต์ <br>
       - วันที่เวลาที่เริ่มอีเวนต์ <br>
       - วันเวลาที่สิ้นสุดอีเวนต์
  * 19.b มีหน้าแก้ไขข้อมูลอีเวนต์ (ข้อมูลตามข้อ 18a.)
  * 19.c  มีส่วนจัดการ “การเปิดรับผู้เข้าร่วมอีเวนต์” (เช่น ผู้ร่วมสัมมนา ผู้เข้าแข่งขัน) โดยระบุจำนวนผู้เข้าร่วมอีเวนต์สูงสุด และช่วงเวลาเริ่มต้น-สิ้นสุดที่จะเปิดรับผู้เข้าร่วมอีเวนต์
* 6510451077 นางสาวอภิญญา ลิ้มฮวบ bvnnal
    * หน้า Login 
    * GUI ของ Event หน้าต่างๆ
    * 11.b ข้อมูลคําสั่งหรือคําแนะนําในการใช้โปรแกรมที่นิสิตสร้างขึ้นมา
    * 17.d มีหน้าแสดงรายละเอียดของอีเวนต์ที่ผู้ใช้เลือกจากข้อ (17a.) แสดงรายละเอียดที่เหมาะสม และผู้ใช้สามารถเข้าร่วมอีเวนต์ได้หากมีที่ว่างเหลืออยู่ (หากไม่มีที่ว่างเหลือต้องไม่ให้เข้าร่วม)หากเข้าร่วมอีเวนต์แล้ว ให้แสดง “ตารางกิจกรรมของอีเวนต์”
    * 22 ผู้เข้าทีม เห็นชื่อทีม และ“ตารางกิจกรรมของอีเวนต์สำหรับผู้ร่วมทีม<ชื่อทีม>” และสามารถแสดงความคิดเห็นภายในกิจกรรม ซึ่งสมาชิกทุกคนในทีมรวมถึงผู้จัดอีเวนต์สามารถดูความคิดเห็นภายในกิจกรรมและโต้ตอบกันได้
* 6510451085 นายอภิสิทธิ์ ประเสริฐเวศยากร Professors001
    * 11.a ข้อมูลนิสิตผู้จัดทําโปรแกรม ได้แก่
        * รูปที่แสดงหน้าตา (หน้ายิ้ม) ที่ชัดเจน
        * ชื่อ นามสกุล และชื่อเล่น
        * รหัสนิสิต 
    * 15 ระบบของผู้ดูแลระบบ ระบบส่วนนี้ถูกจำกัดสิทธิ์สำหรับผู้ดูแลระบบเท่านั้น ผู้ใช้ที่ไม่ใช่ผู้ดูแลระบบต้องไม่สามารถเข้าใช้งานส่วนนี้
        * 15.b ผู้ดูแลระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้ หลังจาก Login โดยต้องระบุ password เดิมที่ถูกต้องด้วย พร้อมระบุรหัสผ่านใหม่ การยืนยันรหัสใหม่ที่ตรงกัน จึงจะเปลี่ยนรหัสผ่านได้ และรหัสผ่านใหม่ต้องใช้ได้
        * 15.c มีหน้าแสดงรายชื่อของผู้ใช้ระบบ โดยต้องแสดงภาพของผู้ใช้ ชื่อสำหรับเข้าสู่ระบบ (username) ชื่อผู้ใช้ระบบ และวันเวลาที่เข้าใช้ล่าสุดของผู้ใช้ระบบ เรียงลำดับตามวันเวลาที่เข้าใช้ระบบล่าสุดก่อน
    * 16.b ผู้ใช้ระบบสามารถเปลี่ยนรหัสผ่านของตนเองได้ หลังจาก Login โดยต้องระบุ password เดิมที่ถูกต้องด้วย พร้อมระบุรหัสผ่านใหม่ การยืนยันรหัสใหม่ที่ตรงกัน จึงจะเปลี่ยนรหัสผ่านได้ และรหัสผ่านใหม่ต้องใช้ได้
    * 19 การจัดการอีเวนต์
        * 19.a ต้องเป็น ”ผู้จัดอีเวนต์” นี้เท่านั้นจึงจะจัดการอีเวนต์นี้ได้
        * 19.e มีส่วนจัดการ “การเปิดรับทีมผู้ร่วมจัดอีเวนต์” (เช่น ทีมอาสาสมัคร) โดยระบุชื่อทีม จำนวนผู้ร่วมทีมสูงสุด และช่วงเวลาเริ่มต้น-สิ้นสุดที่จะเปิดรับผู้ร่วมทีม ซึ่งสร้างได้หลายทีม และชื่อทีมต้องไม่ซ้ำกันในอีเวนต์เดียวกัน
    * 20  การจัดการผู้เข้าร่วมอีเวนต์
        * 20.a  มีหน้าแสดงรายชื่อผู้เข้าร่วมอีเวนต์ ผู้จัดอีเวนต์สามารถระงับสิทธิ์ผู้เข้าร่วมอีเวนต์ได้ ผู้เข้าร่วมอีเวนต์ที่ถูกระงับสิทธิ์จะไม่เห็น “ตารางกิจกรรมของอีเวนต์สำหรับผู้เข้าร่วมอีเวนต์”
        * 20.b มีหน้าแสดง และหน้าจัดการ (เพิ่มและลบ) “ตารางกิจกรรมของอีเวนต์สำหรับผู้เข้าร่วมอีเวนต์” โดยระบุข้อมูลต่อไปนี้
            * เวลาเริ่มต้น-สิ้นสุดของกิจกรรม
            * ชื่อกิจกรรม
            * รายละเอียดกิจกรรม
    * 21 การจัดการทีมผู้ร่วมจัดอีเวนต์
        * 21.a มีหน้าแสดงทีมผู้ร่วมจัดอีเวนต์ และแสดงรายชื่อผู้ร่วมทีมในทีมนั้น ผู้จัดอีเวนต์สามารถระงับสิทธิ์ผู้ร่วมทีมได้ ผู้เข้าร่วมอีเวนต์ที่ถูกระงับสิทธิ์จะไม่เห็น “ตารางกิจกรรมของอีเวนต์สำหรับผู้ร่วมทีม<ชื่อทีม>”
        * 21.b มีหน้าแสดง และหน้าจัดการ (เพิ่มและลบ) “ตารางกิจกรรมของอีเวนต์สำหรับผู้ร่วมทีม<ชื่อทีม>” (แต่ละทีมมีตารางกิจกรรมแตกต่างกัน) โดยระบุข้อมูลต่อไปนี้
            - ชื่อกิจกรรม
            - รายละเอียดกิจกรรม
        * 21.c ผู้จัดอีเวนต์กำหนดได้ว่า กิจกรรมใดเสร็จสิ้นไปแล้ว กิจกรรมที่เสร็จสิ้นแล้วต้องแสดงให้เห็นชัดเจนว่าเสร็จสิ้นแล้วใน “ตารางกิจกรรมของอีเวนต์สำหรับผู้ร่วมทีม<ชื่อทีม>”



## การวางโครงสร้างไฟล์

cs211-661-project-nebb-group <br>
|--data <br>
|&emsp;|-- EventPicture <br>
|&emsp;|&emsp;|-- เก็บรูปภาพของงานแต่ละEvent <br>
|&emsp;|-- UserProfilePicture <br>
|&emsp;|&emsp;|-- เก็บรูปภาพของUserทั้งหมดในProgram <br>
|&emsp;|-- จะเก็บพวกไฟล์CSVที่บันทึกข้อมูลต่างๆทั้งหมดของ NEBB Application  <br>
|-- src <br>
|&emsp;|-- main <br>
|&emsp;|&emsp;|-- java <br>
|&emsp;|&emsp;|&emsp;|-- cs211 <br>
|&emsp;|&emsp;|&emsp;|&emsp;|-- project <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|-- controllers <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- ในFolderนี้จะทำการเก็บController .java ทั้งหมดที่ใช้ในNEBB Application  <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|-- cs211661project <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- ในFolderนี้จะทำการเก็บตัวHelloApplication ที่จะเป็นตัวกลางในการเข้าถึงหน้าต่างๆใน NEBB Application <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|-- models <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- chats <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัวModel ที่เกี่ยวข้องกับ ระบบ แชท <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- eventHub <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัวModel ที่เกี่ยวข้องกับ ระบบ event ตารางกิจกรรม และ ตัวเชื่อมระหว่าง user กับ event <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- team <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัวModel ที่เกี่ยวข้องกับ ระบบ สมาชิกทีมใน Event <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|-- users <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัวModel ที่เกี่ยวข้องกับ ระบบ user ที่ใช้ในการระบุตัว และ login เข้าใช้งาน <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;|-- services <br>
|&emsp;|&emsp;|&emsp;|&emsp;&emsp;&emsp;|-- ในFolderนี้จะทำการเก็บพวกคำสั่งที่ใช้ในการอ่านเขียนไฟล์ รวมถึง ตัวเรียงลำดับเวลา และในส่วนของ FXRouter <br>
|&emsp;|&emsp;|&emsp;|-- module-info.java <br>
|&emsp;|&emsp;|-- resources  <br>
|&emsp;|&emsp;&emsp;|-- buttom_style <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- เก็บตัวCSS ของEffect ในตัวฺปุ่มต่างๆในโปรแกรม <br>
|&emsp;|&emsp;&emsp;|-- cs211 <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- project <br>
|&emsp;|&emsp;&emsp;|&emsp;&emsp;|-- views <br>
|&emsp;|&emsp;&emsp;|&emsp;&emsp;&emsp;|-- เก็บตัว FXML ทั้งหมดที่ถูกใช้งานบน NEBB Application <br>
|&emsp;|&emsp;&emsp;|-- icon-png <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- home-png <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- home <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|&emsp;|-- เก็บตัวโลโก้Home <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัว โลโก้ที่ใช้ในทุกๆหน้า ตรงแถบด้านล่าง <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- logo_png <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัว โลโก้ทNEBB <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- profile-png <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัว โลโก้รูปโปรไฟล์กับรูปTicket <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- team-png <br>
|&emsp;|&emsp;&emsp;|&emsp;|&emsp;|-- เก็บตัว โลโก้รูปทีม <br>
|&emsp;|&emsp;&emsp;|-- images <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- เก็บรูปของผู้พัฒนาโปรแกรม <br>
|&emsp;|&emsp;&emsp;|-- NEBB_tutorial <br>
|&emsp;|&emsp;&emsp;|&emsp;|-- เก็บรูปของตังอย่างและการสอนใช้งาน NEBB Application <br>
|&emsp;|&emsp;&emsp;|-- table_style <br>
|&emsp;|&emsp;&emsp;&emsp;|-- เก็บลักษณะGUIของTableView <br>
|-- README.md <br>



## วิธีการติดตั้งหรือรันโปรแกรม
    
1.กดเข้าลิงค์ด้านล่าง เพื่อดาวน์โหลดโปรแกรมมาใช้
   ```
   https://github.com/CS211-661/cs211-661-project-nebb-group/releases/tag/final1.0
   ```
2.จากนั้นกด ตัว "NEBB.Application.zip" เพื่อดาวน์โหลดApplicationมาใช้ <br>
3.ให้Unzip ไฟล์ที่ดาวน์โหลดมา จะได้มา 1 ไฟล์ กับ 1 Folder
* Folder data จะเป็น Folder ที่เก็บข้อมูลทั้งหมดในApplication
* NEBB.jar จะเป็น ตัว Program ที่พวกเราได้พัฒนาขึ้นมา
4.จากนั้น ให้กด Double Click ที่ NEBB.jar เพื่อเข้าใช้งาน Program <br>
  
  * (หากไม่สามรถเข้าใช้งานผ่านDouble Click ได้ ให้ทำตามนี้) <br>
    * 1.เปิดTerminal หรือ Command Prompt
    * 2.จากนั้นให้ ใช้คำสั่ง cd ไปยังFolderที่จัดเก็บตัว NEBB.jar
    * 3.ใช้คำสั่งในการ run Program 

       ```
        java -jar NEBB.jar
       ```

## ตัวอย่างข้อมูลผู้ใช้ระบบ
* Admin
  * Username: admin 
  * Password: admin 
* Event Owner
  * Username: brofessors
  * Password: 170447
* Event Audience
  * Username: 9arm
  * Password: 9arm
* Event Audience BANNED
  * Username: mayaCreator
  * Password: 1234
* Event Team Member(Demon Team)
  * Username: ithikornn
  * Password: 1234
* Event Team Member(Angel Team)
  * Username: pepTheBald
  * Password: pep
* Event Team Member(Angel Team) BANNED
  * Username: tentlnwza007
  * Password: 1234


## สรุปสิ่งที่พัฒนาแต่ละครั้งที่นำเสนอความก้าวหน้าของระบบ
* ครั้งที่ 1 (11 สิงหาคม 2566)
  * ออกแบบโครงสร้างโปรแกรมเบื้องต้น 
    * ( พัฒนาร่วมกัน )
  * design หน้าตาแอป,ธีมแอป 
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สร้าง Login เบื้องต้น 
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )
  * สร้าง Register เบื้องต้น 
    * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * สร้าง Models User,userlist 
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )

( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )
( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
( พัฒนาร่วมกัน )

* ครั้งที่ 2 (1 กันยายน 2566)
  * เริ่มเขียนไฟล์ ใน CSV (userlist) 
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * เพิ่มหน้าต่างๆของแอป  
    * ( พัฒนาร่วมกัน )
  * หน้าหลักแสดงอีเวนท์ได้
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * ค้นหาอีเวนท์ได้ ในช่องค้นหา
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สร้างหน้าของแอดมินแสดง userlist และ แก้ไขได้
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้างหน้าโปรไฟล์ แก้ไขข้อมูลต่างๆได้ เช่น รูป ชื่อ รหัสผ่าน
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้าง Models event,eventlist
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * Register ทำงานได้สมบูรณ์
    * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * login ทำงานได้สมบูรณ์ ใน csv
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )

* ครั้งที่ 3 (22 กันยายน 2566)
  * สร้าง Datasource member user event team calendar chatlist
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้าง Models memeber,memberlist chat,chatlist
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้าง Models team,teamlist,teamstaff
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * เพิ่มหน้า Calendar ที่ใช้งานได้ มีการบอกกิจกรรมชััดเจน
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * เพิ่มหน้า Chat ที่ใช้งานได้ พิมและส่งข้อความได้เก็บไว้ใน CSV
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )
  * สร้าง Calendar ในหน้า createCalendar
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้าง Event ในหน้า crateEvent ได้และเขียนลง CSV
    * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * สามารถ join Event ได้
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )
  * เพิ่มหน้า manageEvent
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สามารถจัดการอีเวนท์ ในหน้า manageEvent ที่แสดงรายละเอียดต่างๆ เช่น ลูกค้า และ ทีม
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้างทีมได้ ในอีเวนท์นั้นๆ
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * เพิ่ม Teammanage ดูทีมได้
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * หน้า Booking history สมบุรณ์ สามารถเช็คอีเวนท์ที่กำลังจะเริ่มและจบไปแล้วได้
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สามารถแก้ไขข้อมูลรายละเอียดอีเวนท์ได้
    * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * สามารถดูรายละเอียดอีเวนท์ได้
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )

* ครั้งที่ 4 (13 ตุลาคม 2566)
  * มีการเพิ่ม Grid ในการแสดงข้อมูลในหน้าต่างๆ
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สร้าง Grid ต่างๆในการแสดงข้อมูลแบบละเอียด 
    * ( พัฒนาโดย นางสาว อภิญญา ลิ้มฮวบ )
  * แก้ไขระบบ Calendar 
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * เพิ่มระบบการจองให้ลิ้งกับเวลาที่ตั้ง
    * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * manageTeam สมบูรณ์
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สามารถ แบน ผู้ใช้ได้
    * ( พัฒนาโดย นาย อภิสิทธิ์ ประเสริฐเวศยากร )
  * สามารถ check username ว่าใช้งานได้หรือไม่ในหน้า register
      * ( พัฒนาโดย นางสาว นุตประวีณ์ กาวี )
  * เพิ่มหน้า คู่มือ ในการใช้แอป
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สร้างหน้า credit แสดงผู้พัฒนา
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  * สร้างคู่มือการใช้งาน PDF 
    * ( พัฒนาโดย นาย ทิเบต เจริญศรีไพบูลย์ )
  


   
