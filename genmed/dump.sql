| address               |
| addressOfUser         |
| drugBatch             |
| drugComponents        |
| drugs                 |
| genericDrugComposition |
| genericDrugs          |
| itemsOrdered          |
| orders                |
| owner                 |
| reviews               |
| shop                  |
| shopInventory         |
| shopPhone             |
| user                  |
| userPhone

insert into drugComponents(comp_name) values ('CompA');
insert into drugComponents(comp_name) values ('CompB');
insert into drugComponents(comp_name) values ('CompC');

insert into genericDrugs(name) values ('GenA');
insert into genericDrugs(name) values ('GenB');
insert into genericDrugs(name) values ('GenC');

insert into genericDrugComposition(gen_id, comp_id, percent) values (1,1,20);
insert into genericDrugComposition(gen_id, comp_id, percent) values (1,3,80);
insert into genericDrugComposition(gen_id, comp_id, percent) values (2,1,20);
insert into genericDrugComposition(gen_id, comp_id, percent) values (2,2,50);
insert into genericDrugComposition(gen_id, comp_id, percent) values (2,3,30);
insert into genericDrugComposition(gen_id, comp_id, percent) values (3,3,90);

insert into drugs(name, mf_name, is_generic, gen_id) values ('DrugA', 'MakeA', 1, 1);
insert into drugs(name, mf_name, is_generic, gen_id) values ('DrugB', 'MakeA', 0, 1);
insert into drugs(name, mf_name, is_generic, gen_id) values ('DrugC', 'MakeB', 1, 2);
insert into drugs(name, mf_name, is_generic, gen_id) values ('DrugD', 'MakeC', 0, 3);
